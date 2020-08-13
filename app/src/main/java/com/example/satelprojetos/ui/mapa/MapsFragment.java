package com.example.satelprojetos.ui.mapa;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.satelprojetos.R;
import com.example.satelprojetos.helper.EnviadoDAO;
import com.example.satelprojetos.helper.FormularioDAO;
import com.example.satelprojetos.model.Formulario;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlLayer;
import com.google.maps.android.data.kml.KmlLineString;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPoint;
import com.google.maps.android.geometry.Point;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private List<Formulario> listaFormularioCadastro = new ArrayList<>();
    private List<Formulario> listaFormularioEnvio = new ArrayList<>();
    private List<String> listaLatitude = new ArrayList<>();
    private List<String> listaLongitude = new ArrayList<>();
    private List<String> listaCodigo = new ArrayList<>();
    private GoogleMap mMap;
    private static final int REQUEST_CODE = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;
    Geocoder geocoder;
    List<Address> addresses;
    List<LatLng> pathPoints;
    ProgressDialog loading;
    android.location.Location lastKnownLocation;
    private Location localizacao;
    KmlLayer kmlLayer;

    SupportMapFragment mapFragment;

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_maps, container, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        getItems();
        FormularioDAO formularioDAO = new FormularioDAO(getActivity().getApplicationContext());
        listaFormularioCadastro = formularioDAO.listar();
        EnviadoDAO enviadoDAO = new EnviadoDAO(getActivity().getApplicationContext());
        listaFormularioEnvio = enviadoDAO.listar();
        verificarPermissaoLocaliza();
        if (mapFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        return root;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        InputStream kmlInputStream = getResources().openRawResource(R.raw.doc);
        int a = 0;
        try {
            kmlLayer = new KmlLayer(mMap, kmlInputStream, requireContext());
            kmlLayer.addLayerToMap();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listaFormularioCadastro.size(); i++) {
            Log.i("TESTE", "Loop");
            LatLng local = new LatLng(Double.parseDouble(listaFormularioCadastro.get(i).getLatitude().replace(",", ".")), Double.parseDouble(listaFormularioCadastro.get(i).getLongitude().replace(",", ".")));
            mMap.addMarker(new MarkerOptions()
                    .position(local)
                    .title(String.valueOf(i))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
        for (int i = 0; i < listaFormularioEnvio.size(); i++) {
            Log.i("TESTE", "Loop");
            LatLng local = new LatLng(Double.parseDouble(listaFormularioEnvio.get(i).getLatitude().replace(",", ".")), Double.parseDouble(listaFormularioEnvio.get(i).getLongitude().replace(",", ".")));
            mMap.addMarker(new MarkerOptions()
                    .position(local)
                    .title(String.valueOf(i))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public  boolean onMarkerClick(Marker marker) {
                Log.i("MARKER","FUNCIONOU");
                return true;
            }
        });
        kmlLayer.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
            @Override
            public void onFeatureClick(Feature feature) {
                feature.getGeometry();

                Log.i("KmlClick", "Feature clicked: Lat:");
                //Log.i("KmlClick", "Feature clicked: " + mMap.getCameraPosition().target);
                ;
            }
        });
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("TESTE", "ENTREI AQUI2");
                localizacao = location;
                LatLng myLocal = new LatLng(localizacao.getLatitude(), localizacao.getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(myLocal)
                        .title("201979475")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocal));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i("TESTE", "ENTREI AQUI3");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.i("TESTE", "ENTREI AQUI4");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(requireActivity().getApplicationContext(), "Por favor ative seu GPS", Toast.LENGTH_SHORT).show();
            }
        };
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.i("TESTE", "ENTREI AQUI");
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10000,
                    10,
                    locationListener);

        }
        geocoder = new Geocoder(requireContext(), Locale.getDefault());
    }


    public Boolean verificarPermissaoLocaliza() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(requireActivity().getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireActivity().getApplicationContext(),
                permissions[1]) == PackageManager.PERMISSION_GRANTED
        ) {
            return true;


        } else {
            ActivityCompat.requestPermissions(requireActivity(), permissions, REQUEST_CODE);
            return false;
        }
    }

    private void getItems() {

        loading = ProgressDialog.show(requireContext(), "Loading", "por favor espere", false, true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbzZJUnvHaDYfO13T9t7NyhLcweYuuYp38D1n0JzH0Hs4FVR0mrO/exec?action=getItems&email=" + FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        queue.add(stringRequest);

    }


    private void parseItems(String jsonResposnce) {

        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String latitude = jo.getString("latitude");
                String longitude = jo.getString("longitude");
                String codigo = jo.getString("codigo");


                /*HashMap<String, String> item = new HashMap<>();
                item.put("latitude", latitude);
                HashMap<String, String> item2 = new HashMap<>();
                item2.put("latitude", latitude);
                HashMap<String, String> item3 = new HashMap<>();

                item3.put("latitude", latitude);*/
                listaLatitude.add(latitude);
                listaLongitude.add(longitude);
                listaCodigo.add(codigo);



            }
            for(int i=0; i<listaLatitude.size();i++){
                LatLng local = new LatLng(Double.parseDouble(listaLatitude.get(i)),Double.parseDouble(listaLongitude.get(i)));
                mMap.addMarker(new MarkerOptions()
                        .position(local)
                        .title(listaCodigo.get(i))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        loading.dismiss();
    }
}