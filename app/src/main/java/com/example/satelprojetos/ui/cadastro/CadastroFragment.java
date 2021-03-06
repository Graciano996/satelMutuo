package com.example.satelprojetos.ui.cadastro;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.BitmapCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.satelprojetos.R;
import com.example.satelprojetos.activity.DrawerActivity;
import com.example.satelprojetos.activity.MainActivity;
import com.example.satelprojetos.config.ConfiguracaoFirebase;
import com.example.satelprojetos.helper.Base64Custom;
import com.example.satelprojetos.helper.FormularioDAO;
import com.example.satelprojetos.model.Formulario;
import com.example.satelprojetos.ui.cadastrados.CadastradosFragment;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLStreamHandlerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage;

public class CadastroFragment extends Fragment {
    private static final int REQUEST_CODE = 1;
    private static final int IMAGE_CAPTURE_CODE = 2;
    private static final int IMAGE_PICK_CODE = 3;
    private static final int IMAGE_CAPTURE_CODE2 = 4;
    private static final int IMAGE_PICK_CODE2 = 5;
    private static final int IMAGE_CAPTURE_CODE3 = 6;
    private static final int IMAGE_PICK_CODE3 = 7;
    private static final int IMAGE_CAPTURE_CODE4 = 8;
    private static final int IMAGE_PICK_CODE4 = 9;
    private static final int IMAGE_CAPTURE_CODE5 = 10;
    private static final int IMAGE_PICK_CODE5 = 11;
    private static final int IMAGE_CAPTURE_CODE6 = 12;
    private static final int IMAGE_PICK_CODE6 = 13;
    private static final int IMAGE_CAPTURE_CODE7 = 14;
    private static final int IMAGE_PICK_CODE7 = 15;
    private static final int IMAGE_CAPTURE_CODE8 = 16;
    private static final int IMAGE_PICK_CODE8 = 17;
    private static final int IMAGE_CAPTURE_CODE9 = 18;
    private static final int IMAGE_PICK_CODE9 = 19;
    private static final int IMAGE_CAPTURE_CODE10 = 20;
    private static final int IMAGE_PICK_CODE10 = 21;
    private static final int IMAGE_CAPTURE_CODE11 = 22;
    private static final int IMAGE_PICK_CODE11 = 23;
    private static final int IMAGE_CAPTURE_CODE12 = 24;
    private static final int IMAGE_PICK_CODE12 = 25;
    private static final int IMAGE_CAPTURE_CODE13 = 26;
    private static final int IMAGE_PICK_CODE13 = 27;
    private static final int IMAGE_CAPTURE_CODE14 = 28;
    private static final int IMAGE_PICK_CODE14 = 29;
    private static final int IMAGE_CAPTURE_CODE15 = 30;
    private static final int IMAGE_PICK_CODE15 = 31;
    private static final int IMAGE_CAPTURE_CODE16 = 32;
    private static final int IMAGE_PICK_CODE16 = 33;
    private static final int IMAGE_CAPTURE_CODE17 = 34;
    private static final int IMAGE_PICK_CODE17 = 35;
    private static final int IMAGE_CAPTURE_CODE18 = 36;
    private static final int IMAGE_PICK_CODE18 = 37;
    private static final int IMAGE_CAPTURE_CODE19 = 38;
    private static final int IMAGE_PICK_CODE19 = 39;
    private static final int IMAGE_CAPTURE_CODE20 = 40;
    private static final int IMAGE_PICK_CODE20 = 41;
    private static final int IMAGE_CAPTURE_CODE21 = 42;
    private static final int IMAGE_PICK_CODE21 = 43;
    private static final int IMAGE_CAPTURE_CODE22 = 44;
    private static final int IMAGE_PICK_CODE22 = 45;
    private static final int IMAGE_CAPTURE_CODE23 = 46;
    private static final int IMAGE_PICK_CODE23 = 47;
    private static final int IMAGE_CAPTURE_CODE24 = 48;
    private static final int IMAGE_PICK_CODE24 = 49;
    private static final int IMAGE_CAPTURE_CODE25 = 50;
    private static final int IMAGE_PICK_CODE25 = 51;
    private static final int IMAGE_CAPTURE_CODE26 = 52;
    private static final int IMAGE_PICK_CODE26 = 53;
    private static final int IMAGE_CAPTURE_CODE27 = 54;
    private static final int IMAGE_PICK_CODE27 = 55;
    private static final int IMAGE_CAPTURE_CODE28 = 56;
    private static final int IMAGE_PICK_CODE28 = 57;
    private static final int IMAGE_CAPTURE_CODE29 = 58;
    private static final int IMAGE_PICK_CODE29 = 59;
    private static final int IMAGE_CAPTURE_CODE30 = 60;
    private static final int IMAGE_PICK_CODE30 = 61;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private StorageReference storageReference;
    private FirebaseAuth autentificacao;
    private ProgressDialog progressDialog;
    Geocoder geocoder;
    List<Address> addresses;
    private EditText codigo, endereco, latitude, longitude, observacaoFisicas,
            observacaoAtivos, quantidadeLampada, quantidadeLampada2, quantidadeLampada3,
            potReator, potReator2, potReator3, quantidade24H, quantidade24H2, quantidade24H3,
            observacaoVegetacao, observacaoIP,outros,
            quantidadeCabos, quantidadeCabos2, quantidadeCabos2dois, quantidadeCabos3, quantidadeCabos4, quantidadeCabos5,
            nome, nome2, nome3, nome4, nome5, descricaoIrregularidade, descricaoIrregularidade2,
            descricaoIrregularidade3, descricaoIrregularidade4, descricaoIrregularidade5,
            observacaoMutuo, observacaoMutuo2, observacaoMutuo3, observacaoMutuo4, observacaoMutuo5;
    private Spinner municipio, alturaCarga, tipoPoste, ipEstrutura, ipEstrutura2, ipEstrutura3, tipoPot,
            tipoPot2, tipoPot3, dimensaoVegetacao, ipAtivacao, ipAtivacao2, ipAtivacao3,
            trafoTrifasico, trafoMono, ramalSubt, quantidadeOcupantes,
            tipoCabo, tipoCabo2, tipoCabo2dois, tipoCabo3, tipoCabo4, tipoCabo5, finalidade, finalidade2, finalidade3,
            finalidade4, finalidade5, ceans, ceans2, ceans3, ceans4, ceans5, tar, tar2, tar3, tar4,
            tar5, reservaTec, reservaTec2, reservaTec3, reservaTec4, reservaTec5, backbone,
            backbone2, backbone3, backbone4, backbone5, distaciaBaixa, distanciaMedia, estadoArvore,
            localArvore;
    private CheckBox normal, ferragemExposta, fletido, danificado, abalroado, trincado, religador, medicao,
            chFusivel, chFaca, vinteEQuatro, vinteEQuatro2, vinteEQuatro3,
            ativos, chkTrafoTrifasico, chkTrafoMono, ip, ip2, ip3, chFusivelReligador, chBanco, mutuo,
            placaIdentificadora, placaIdentificadora2, placaIdentificadora3, placaIdentificadora4,
            placaIdentificadora5, descidaCabos, descidaCabos2, descidaCabos3, descidaCabos4, descidaCabos5,
            quedaArvore;
    private Formulario formularioAtual;
    private Boolean controle = false;
    private TextView mutuo2, mutuo3, mutuo4, mutuo5;
    private List<ImageView> listaLatitude = new ArrayList<>();
    private File photoFile = null;
    private List<Bitmap> imagemF = new ArrayList<>();
    private List<Uri> urlF = new ArrayList<>();
    private List<Boolean> novoU = new ArrayList<>();
    private Location localizacao;
    private String codigoEnergisa ="";
    public int contadorIp = 1, contadorPan = 1, contadorAt = 1;
    public int codigoSetorUpload;
    private ImageView foto, foto2, foto3, foto4, foto5, foto6, foto7, foto8, foto9, foto10, foto11,
            foto12, foto13, foto14,foto15, foto16, foto17,foto18, foto19, foto20,foto21, foto22, foto23
            ,foto24, foto25, foto26,foto27, foto28, foto29, foto30;
    private Uri urlFoto, urlFoto2, urlFoto3, urlFoto4,urlFoto5, urlFoto6, urlFoto7, urlFoto8,
            urlFoto9,urlFoto10, urlFoto11, urlFoto12, urlFoto13, urlFoto14, urlFoto15, urlFoto16, urlFoto17
            , urlFoto18, urlFoto19, urlFoto20, urlFoto21, urlFoto22, urlFoto23, urlFoto24, urlFoto25, urlFoto26
            , urlFoto27, urlFoto28, urlFoto29, urlFoto30;
    private Bitmap imagem, imagem2, imagem3, imagem4,imagem5, imagem6, imagem7, imagem8, imagem9,
            imagem10, imagem11, imagem12, imagem13, imagem14, imagem15, imagem16, imagem17
            , imagem18, imagem19, imagem20, imagem21, imagem22, imagem23, imagem24, imagem25, imagem26
            , imagem27, imagem28, imagem29, imagem30;
    private String imgPath, imgPath2, imgPath3,imgPath4,imgPath5, imgPath6, imgPath7, imgPath8,
            imgPath9,imgPath10, imgPath11, imgPath12, imgPath13, imgPath14, imgPath15, imgPath16, imgPath17
            , imgPath18, imgPath19, imgPath20, imgPath21, imgPath22, imgPath23, imgPath24, imgPath25, imgPath26
            , imgPath27, imgPath28, imgPath29, imgPath30;
    private Boolean novoUpload = false, novoUpload2 = false, novoUpload3 = false, novoUpload4 = false,
            novoUpload5 = false, novoUpload6 = false, novoUpload7 = false, novoUpload8 = false, novoUpload9 = false,
            novoUpload10 = false, novoUpload11 = false, novoUpload12 = false, novoUpload13 = false, novoUpload14 = false
            , novoUpload15 = false, novoUpload16 = false, novoUpload17 = false
            , novoUpload18 = false, novoUpload19 = false, novoUpload20 = false
            , novoUpload21 = false, novoUpload22 = false, novoUpload23 = false
            , novoUpload24 = false, novoUpload25 = false, novoUpload26 = false
            , novoUpload27 = false, novoUpload28 = false, novoUpload29 = false
            ,novoUpload30 = false;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        final View root = inflater.inflate(R.layout.fragment_cadastro, container, false);
        codigo = root.findViewById(R.id.textCadastroCodigo);
        foto = root.findViewById(R.id.imageCadastroFoto);
        foto2 = root.findViewById(R.id.imageCadastroFoto2);
        foto3 = root.findViewById(R.id.imageCadastroFoto3);
        foto4 = root.findViewById(R.id.imageCadastroFoto4);
        foto5 = root.findViewById(R.id.imageCadastroFoto5);
        foto6 = root.findViewById(R.id.imageCadastroFoto6);
        foto7 = root.findViewById(R.id.imageCadastroFoto7);
        foto8 = root.findViewById(R.id.imageCadastroFoto8);
        foto9 = root.findViewById(R.id.imageCadastroFoto9);
        foto10 = root.findViewById(R.id.imageCadastroFoto10);
        foto11 = root.findViewById(R.id.imageCadastroFoto11);
        foto12 = root.findViewById(R.id.imageCadastroFoto12);
        foto13 = root.findViewById(R.id.imageCadastroFoto13);
        foto14 = root.findViewById(R.id.imageCadastroFoto14);
        foto15 = root.findViewById(R.id.imageCadastroFoto15);
        foto16 = root.findViewById(R.id.imageCadastroFoto16);
        foto17 = root.findViewById(R.id.imageCadastroFoto17);
        foto18 = root.findViewById(R.id.imageCadastroFoto18);
        foto19 = root.findViewById(R.id.imageCadastroFoto19);
        foto20 = root.findViewById(R.id.imageCadastroFoto20);
        foto21 = root.findViewById(R.id.imageCadastroFoto21);
        foto22 = root.findViewById(R.id.imageCadastroFoto22);
        foto23 = root.findViewById(R.id.imageCadastroFoto23);
        foto24 = root.findViewById(R.id.imageCadastroFoto24);
        foto25 = root.findViewById(R.id.imageCadastroFoto25);
        foto26 = root.findViewById(R.id.imageCadastroFoto26);
        foto27 = root.findViewById(R.id.imageCadastroFoto27);
        foto28 = root.findViewById(R.id.imageCadastroFoto28);
        foto29 = root.findViewById(R.id.imageCadastroFoto29);
        foto30 = root.findViewById(R.id.imageCadastroFoto30);
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("TESTE","ENTREI AQUI2");
                localizacao = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i("TESTE","ENTREI AQUI3");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.i("TESTE","ENTREI AQUI4");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(requireActivity().getApplicationContext(), "Por favor ative seu GPS", Toast.LENGTH_SHORT).show();
            }
        };
        geocoder = new Geocoder(requireContext(),Locale.getDefault());
        if (verificarPermissaoLocaliza()) {

            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                Log.i("TESTE","ENTREI AQUI");
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        5000,
                        2,
                        locationListener);
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        5000,
                        2,
                        locationListener);

            }



        }
        Button btnLocaliza = root.findViewById(R.id.btnCadastroGetMap);
        btnLocaliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (localizacao == null) {
                    Toast.makeText(requireActivity().getApplicationContext(), "Não é possui utilizar essa função no estado de economia de energia", Toast.LENGTH_SHORT).show();
                } else {
                    latitude.setText(String.format("%.5f",localizacao.getLatitude()));
                    longitude.setText(String.format("%.5f",localizacao.getLongitude()));
                    try {
                        addresses = geocoder.getFromLocation(localizacao.getLatitude(), localizacao.getLongitude(), 1);
                        endereco.setText(addresses.get(0).getAddressLine(0));
                        String municipioTexto = addresses.get(0).getAdminArea();
                        for (int i = 0; i < municipio.getAdapter().getCount(); i++) {
                            municipio.setSelection(i);
                            if (municipio.getSelectedItem().toString().equals(municipioTexto)) {
                                break;
                            }
                    }} catch (IOException e) {
                        e.printStackTrace();
                    }

                }}
            });




        storageReference = ConfiguracaoFirebase.getStorageReference();
        autentificacao = ConfiguracaoFirebase.getFirebaseAuth();
        final Button btnFoto = root.findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        Button btnFoto2 = root.findViewById(R.id.btnFoto2);
        btnFoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE2);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE2);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        Button btnFoto3 = root.findViewById(R.id.btnFoto3);
        btnFoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE3);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE3);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto4 = root.findViewById(R.id.btnFoto4);
        btnFoto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE4);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE4);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto5 = root.findViewById(R.id.btnFoto5);
        btnFoto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE5);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE5);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto6 = root.findViewById(R.id.btnFoto6);
        btnFoto6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE6);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE6);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto7 = root.findViewById(R.id.btnFoto7);
        btnFoto7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE7);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE7);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto8 = root.findViewById(R.id.btnFoto8);
        btnFoto8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE8);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE8);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto9 = root.findViewById(R.id.btnFoto9);
        btnFoto9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE9);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE9);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto10 = root.findViewById(R.id.btnFoto10);
        btnFoto10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE10);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE10);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto11 = root.findViewById(R.id.btnFoto11);
        btnFoto11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE11);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE11);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto12 = root.findViewById(R.id.btnFoto12);
        btnFoto12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE12);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE12);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto13 = root.findViewById(R.id.btnFoto13);
        btnFoto13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE13);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE13);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto14 = root.findViewById(R.id.btnFoto14);
        btnFoto14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE14);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE14);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto15 = root.findViewById(R.id.btnFoto15);
        btnFoto15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE15);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE15);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto16 = root.findViewById(R.id.btnFoto16);
        btnFoto16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE16);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE16);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto17 = root.findViewById(R.id.btnFoto17);
        btnFoto17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE17);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE17);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto18 = root.findViewById(R.id.btnFoto18);
        btnFoto18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE18);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE18);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto19 = root.findViewById(R.id.btnFoto19);
        btnFoto19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE19);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE19);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto20 = root.findViewById(R.id.btnFoto20);
        btnFoto20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE20);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE20);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto21 = root.findViewById(R.id.btnFoto21);
        btnFoto21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE21);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE21);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto22 = root.findViewById(R.id.btnFoto22);
        btnFoto22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE22);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE22);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto23 = root.findViewById(R.id.btnFoto23);
        btnFoto23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE23);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE23);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto24 = root.findViewById(R.id.btnFoto24);
        btnFoto24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE24);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE24);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto25 = root.findViewById(R.id.btnFoto25);
        btnFoto25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE25);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE25);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto26 = root.findViewById(R.id.btnFoto26);
        btnFoto26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE26);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE26);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto27 = root.findViewById(R.id.btnFoto27);
        btnFoto27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE27);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE27);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });
        final Button btnFoto28 = root.findViewById(R.id.btnFoto28);
        btnFoto28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE28);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE28);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto29 = root.findViewById(R.id.btnFoto29);
        btnFoto29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE29);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE29);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnFoto30 = root.findViewById(R.id.btnFoto30);
        btnFoto30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarPermissao()){
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                    dialog.setPositiveButton("Tirar foto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarCamera(IMAGE_CAPTURE_CODE30);

                        }
                    });
                    dialog.setNegativeButton("Escolher na galeria", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chamarGaleria(IMAGE_PICK_CODE30);
                        }
                    });
                    dialog.create();
                    dialog.show();
                };
            }
        });

        final Button btnUpload = root.findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem, imgPath,codigo, contadorPan, 1, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem, imgPath,codigo, contadorPan, 1, false, "CS");
                }
            }
        });

        Button btnUpload2 = root.findViewById(R.id.btnUpload2);
        btnUpload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem2,imgPath2, codigo, contadorPan, 2, true, "Pan");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem2, imgPath2, codigo,contadorPan, 2, false, "Pan");
                }
            }
        });

        Button btnUpload3 = root.findViewById(R.id.btnUpload3);
        btnUpload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem3, imgPath3,codigo, contadorPan, 3, true, "Pan");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem3, imgPath3,codigo, contadorPan, 3, false, "Pan");
                }
            }
        });

        final Button btnUpload4 = root.findViewById(R.id.btnUpload4);
        btnUpload4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem4, imgPath4,codigo, contadorIp, 4, true, "IP");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem4, imgPath4,codigo, contadorIp, 4, false, "IP");
                }
            }
        });
        final Button btnUpload5 = root.findViewById(R.id.btnUpload5);
        btnUpload5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem5, imgPath5,codigo, contadorIp, 5, true, "IP");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem5, imgPath5,codigo, contadorIp, 5, false, "IP");
                }
            }
        });
        final Button btnUpload6 = root.findViewById(R.id.btnUpload6);
        btnUpload6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                        upload(imagem6, imgPath6,codigo, contadorIp, 6, true, "IP");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem6, imgPath6,codigo, contadorIp, 6, false, "IP");
                }
            }
        });

        final Button btnUpload7 = root.findViewById(R.id.btnUpload7);
        btnUpload7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                        upload(imagem7, imgPath7,codigo, contadorIp, 7, true, "IP");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem7, imgPath7,codigo, contadorIp, 7, false, "IP");
                }
            }
        });

        final Button btnUpload8 = root.findViewById(R.id.btnUpload8);
        btnUpload8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                        upload(imagem8, imgPath8,codigo, contadorIp, 8, true, "IP");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem8, imgPath8,codigo, contadorIp, 8, false, "IP");
                }
            }
        });
        final Button btnUpload9 = root.findViewById(R.id.btnUpload9);
        btnUpload9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem9, imgPath9,codigo, contadorIp, 9, true, "IP");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem9, imgPath9,codigo, contadorIp, 9, false, "IP");
                }
            }
        });

        final Button btnUpload10 = root.findViewById(R.id.btnUpload10);
        btnUpload10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem10, imgPath10,codigo, contadorIp, 10, true, "IP");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem10, imgPath10,codigo, contadorIp, 10, false, "IP");
                }
            }
        });

        final Button btnUpload11 = root.findViewById(R.id.btnUpload11);
        btnUpload11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem11, imgPath11,codigo, contadorIp, 11, true, "IP");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem11, imgPath11,codigo, contadorIp, 11, false, "IP");
                }
            }
        });

        final Button btnUpload12 = root.findViewById(R.id.btnUpload12);
        btnUpload12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem12, imgPath12,codigo, contadorIp, 12, true, "IP");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem12, imgPath12,codigo, contadorIp, 12, false, "IP");
                }
            }
        });

        final Button btnUpload13 = root.findViewById(R.id.btnUpload13);
        btnUpload13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem13, imgPath13,codigo, contadorAt, 13, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem13, imgPath13,codigo, contadorAt, 13, false, "CS");
                }
            }
        });
        final Button btnUpload14 = root.findViewById(R.id.btnUpload14);
        btnUpload14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem14, imgPath14,codigo, contadorAt, 14, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem14, imgPath14,codigo, contadorAt, 14, false, "CS");
                }
            }
        });

        final Button btnUpload15 = root.findViewById(R.id.btnUpload15);
        btnUpload15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem15, imgPath15,codigo, contadorAt, 15, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem15, imgPath15,codigo, contadorAt, 15, false, "CS");
                }
            }
        });

        final Button btnUpload16 = root.findViewById(R.id.btnUpload16);
        btnUpload16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem16, imgPath16,codigo, contadorAt, 16, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem16, imgPath16,codigo, contadorAt, 16, false, "CS");
                }
            }
        });

        final Button btnUpload17 = root.findViewById(R.id.btnUpload17);
        btnUpload17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem17, imgPath17,codigo, contadorAt, 17, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem17, imgPath17,codigo, contadorAt, 17, false, "CS");
                }
            }
        });

        final Button btnUpload18 = root.findViewById(R.id.btnUpload18);
        btnUpload18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem18, imgPath18,codigo, contadorPan, 18, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem18, imgPath18,codigo, contadorPan, 18, false, "CS");
                }
            }
        });

        final Button btnUpload19 = root.findViewById(R.id.btnUpload19);
        btnUpload19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem19, imgPath19,codigo, contadorAt, 19, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem19, imgPath19,codigo, contadorAt, 19, false, "CS");
                }
            }
        });

        final Button btnUpload20 = root.findViewById(R.id.btnUpload20);
        btnUpload20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem20, imgPath20,codigo, contadorAt, 20, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem20, imgPath20,codigo, contadorAt, 20, false, "CS");
                }
            }
        });

        final Button btnUpload21 = root.findViewById(R.id.btnUpload21);
        btnUpload21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem21, imgPath21,codigo, contadorAt, 21, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem21, imgPath21,codigo, contadorAt, 21, false, "CS");
                }
            }
        });

        final Button btnUpload22 = root.findViewById(R.id.btnUpload22);
        btnUpload22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem22, imgPath22,codigo, contadorAt, 22, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem22, imgPath22,codigo, contadorAt, 22, false, "CS");
                }
            }
        });

        final Button btnUpload23 = root.findViewById(R.id.btnUpload23);
        btnUpload23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem23, imgPath23,codigo, contadorAt, 23, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem23, imgPath23,codigo, contadorAt, 23, false, "CS");
                }
            }
        });

        final Button btnUpload24 = root.findViewById(R.id.btnUpload24);
        btnUpload24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem24, imgPath24,codigo, contadorAt, 24, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem24, imgPath24,codigo, contadorAt, 24, false, "CS");
                }
            }
        });

        final Button btnUpload25 = root.findViewById(R.id.btnUpload25);
        btnUpload25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem25, imgPath25,codigo, contadorAt, 25, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem25, imgPath25,codigo, contadorAt, 25, false, "CS");
                }
            }
        });

        final Button btnUpload26 = root.findViewById(R.id.btnUpload26);
        btnUpload19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem26, imgPath26,codigo, contadorAt, 26, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem26, imgPath26,codigo, contadorAt, 26, false, "CS");
                }
            }
        });

        final Button btnUpload27 = root.findViewById(R.id.btnUpload27);
        btnUpload19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem27, imgPath27,codigo, contadorAt, 27, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem27, imgPath27,codigo, contadorAt, 27, false, "CS");
                }
            }
        });

        final Button btnUpload28 = root.findViewById(R.id.btnUpload28);
        btnUpload28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem28, imgPath28,codigo, contadorAt, 28, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem28, imgPath28,codigo, contadorAt, 28, false, "CS");
                }
            }
        });

        final Button btnUpload29 = root.findViewById(R.id.btnUpload19);
        btnUpload29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem29, imgPath29,codigo, contadorAt, 29, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem29, imgPath29,codigo, contadorAt, 29, false, "CS");
                }
            }
        });

        final Button btnUpload30 = root.findViewById(R.id.btnUpload30);
        btnUpload30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formularioAtual != null) {
                    upload(imagem30, imgPath30,codigo, contadorAt, 30, true, "CS");
                }else{
                    Log.i("UPLOAD", "AAQUI");
                    upload(imagem30, imgPath30,codigo, contadorAt, 30, false, "CS");
                }
            }
        });


        endereco = root.findViewById(R.id.textCadastroEndereco);
        municipio = root.findViewById(R.id.spinCadastroMunicipio);
        latitude = root.findViewById(R.id.textCadastroLatitude);
        longitude = root.findViewById(R.id.textCadastroLongitude);
        alturaCarga= root.findViewById(R.id.spinCadastroAlturaCarga);

        //Fisicas
        tipoPoste = root.findViewById(R.id.spinCadastroTipoPoste);
        normal = root.findViewById(R.id.chkCadastroNormal);
        ferragemExposta = root.findViewById(R.id.chkCadastroFerragem);
        fletido = root.findViewById(R.id.chkCadastroFletido);
        danificado = root.findViewById(R.id.chkCadastroDanificado);
        abalroado = root.findViewById(R.id.chkCadastroAbalrroado);
        trincado = root.findViewById(R.id.chkCadastroTrincado);
        observacaoFisicas = root.findViewById(R.id.textCadastroObservacaoFisicas);

        //Iluminação
        ip = root.findViewById(R.id.chkCadastroIP);
        ipEstrutura = root.findViewById(R.id.spinCadastroIPEstrutura);
        quantidadeLampada = root.findViewById(R.id.textCadastroQuantidadeLampada);
        ipEstrutura.setEnabled(false);
        tipoPot = root.findViewById(R.id.spinCadastroTipoPot);
        tipoPot.setEnabled(false);
        potReator = root.findViewById(R.id.textCadastroPotReator);
        potReator.setEnabled(false);
        tipoPot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] texto =  (tipoPot.getSelectedItem().toString()).split(" ");
                try {
                    potReator.setText(texto[1]);
                }
                catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ipAtivacao = root.findViewById(R.id.spinCadastroIPAtivacao);
        ipAtivacao.setEnabled(false);
        vinteEQuatro = root.findViewById(R.id.chkCadastroVinteEQuatro);
        quantidade24H = root.findViewById(R.id.txtCadastroQuantidade24H);

        ip2 = root.findViewById(R.id.chkCadastroIP2);
        ipEstrutura2 = root.findViewById(R.id.spinCadastroIPEstrutura2);
        quantidadeLampada2 = root.findViewById(R.id.textCadastroQuantidadeLampada2);
        ipEstrutura2.setEnabled(false);
        tipoPot2 = root.findViewById(R.id.spinCadastroTipoPot2);
        tipoPot2.setEnabled(false);
        potReator2 = root.findViewById(R.id.textCadastroPotReator2);
        potReator2.setEnabled(false);
        tipoPot2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] texto =  tipoPot2.getSelectedItem().toString().split(" ");
                try {
                    potReator2.setText(texto[1]);
                }
                catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ipAtivacao2 = root.findViewById(R.id.spinCadastroIPAtivacao2);
        ipAtivacao2.setEnabled(false);
        vinteEQuatro2 = root.findViewById(R.id.chkCadastroVinteEQuatro2);
        quantidade24H2 = root.findViewById(R.id.txtCadastroQuantidade24H2);

        ip3 = root.findViewById(R.id.chkCadastroIP3);
        ipEstrutura3 = root.findViewById(R.id.spinCadastroIPEstrutura3);
        quantidadeLampada3 = root.findViewById(R.id.textCadastroQuantidadeLampada3);
        ipEstrutura3.setEnabled(false);
        tipoPot3 = root.findViewById(R.id.spinCadastroTipoPot3);
        tipoPot3.setEnabled(false);
        potReator3 = root.findViewById(R.id.textCadastroPotReator3);
        potReator3.setEnabled(false);
        tipoPot3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] texto =  tipoPot3.getSelectedItem().toString().split(" ");
                try {
                    potReator3.setText(texto[1]);
                }
                catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ipAtivacao3 = root.findViewById(R.id.spinCadastroIPAtivacao3);
        ipAtivacao3.setEnabled(false);
        vinteEQuatro3 = root.findViewById(R.id.chkCadastroVinteEQuatro3);
        quantidade24H3 = root.findViewById(R.id.txtCadastroQuantidade24H3);
        observacaoIP = root.findViewById(R.id.textCadastroObservacaoIP);

        //TRAFO
        ativos = root.findViewById(R.id.chkAtivos);
        chkTrafoTrifasico = root.findViewById(R.id.chkCadastroTrafoTrifasico);
        chkTrafoMono = root.findViewById(R.id.chkCadastroTrafoMono);
        trafoTrifasico = root.findViewById(R.id.spinCadastroTrafoTrifasico);
        trafoTrifasico.setEnabled(false);
        trafoMono = root.findViewById(R.id.spinCadastroTrafoMono);
        trafoMono.setEnabled(false);
        religador = root.findViewById(R.id.chkCadastroReligador);
        medicao = root.findViewById(R.id.chkCadastroMedicao);
        chFusivel = root.findViewById(R.id.chkCadastroFusivel);
        chFaca = root.findViewById(R.id.chkCadastroFaca);
        chBanco = root.findViewById(R.id.chkCadastroBanco);
        chFusivelReligador = root.findViewById(R.id.chkCadastroFusivelReligador);
        ramalSubt = root.findViewById(R.id.spinCadastroRamalSubt);
        ramalSubt.setEnabled(false);
        outros = root.findViewById(R.id.textCadastroOutrosAtivos);
        observacaoAtivos = root.findViewById(R.id.textCadastroObservacaoAtivo);

        //MUTUO
        mutuo2 = root.findViewById(R.id.lblCadastroMutuo2);
        mutuo3 = root.findViewById(R.id.lblCadastroMutuo3);
        mutuo4 = root.findViewById(R.id.lblCadastroMutuo4);
        mutuo5 = root.findViewById(R.id.lblCadastroMutuo5);
        mutuo = root.findViewById(R.id.chkCadastroMutuo);
        quantidadeOcupantes = root.findViewById(R.id.spinCadastroMutuoOcupantes);
        quantidadeOcupantes.setEnabled(false);

        quantidadeCabos = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos);
        tipoCabo = root.findViewById(R.id.spinCadastroMutuoTipoCabo);
        tipoCabo.setEnabled(false);
        nome = root.findViewById(R.id.textCadastroNome);
        finalidade = root.findViewById(R.id.spinCadastroFinalidade);
        finalidade.setEnabled(false);
        ceans = root.findViewById(R.id.spinCadastroCeans);
        ceans.setEnabled(false);
        tar = root.findViewById(R.id.spinCadastroTar);
        tar.setEnabled(false);
        reservaTec = root.findViewById(R.id.spinCadastroReservaTec);
        reservaTec.setEnabled(false);
        backbone = root.findViewById(R.id.spinCadastroBackbone);
        backbone.setEnabled(false);
        placaIdentificadora = root.findViewById(R.id.chkCadastroPlaca);
        descidaCabos = root.findViewById(R.id.chkCadastroDescidaCabos);
        descricaoIrregularidade = root.findViewById(R.id.textCadastroDescricao);
        observacaoMutuo = root.findViewById(R.id.textCadastroObservacaoMutuo);

        quantidadeCabos2 = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos2);
        tipoCabo2 = root.findViewById(R.id.spinCadastroMutuoTipoCabo2);
        quantidadeCabos2dois = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos2dois);
        tipoCabo2dois = root.findViewById(R.id.spinCadastroMutuoTipoCabo2dois);
        tipoCabo2.setEnabled(false);
        nome2 = root.findViewById(R.id.textCadastroNome2);
        finalidade2 = root.findViewById(R.id.spinCadastroFinalidade2);
        finalidade2.setEnabled(false);
        ceans2 = root.findViewById(R.id.spinCadastroCeans2);
        ceans2.setEnabled(false);
        tar2 = root.findViewById(R.id.spinCadastroTar2);
        tar2.setEnabled(false);
        reservaTec2 = root.findViewById(R.id.spinCadastroReservaTec2);
        reservaTec2.setEnabled(false);
        backbone2 = root.findViewById(R.id.spinCadastroBackbone2);
        backbone2.setEnabled(false);
        placaIdentificadora2 = root.findViewById(R.id.chkCadastroPlaca2);
        descidaCabos2 = root.findViewById(R.id.chkCadastroDescidaCabos2);
        descricaoIrregularidade2 = root.findViewById(R.id.textCadastroDescricao2);
        observacaoMutuo2 = root.findViewById(R.id.textCadastroObservacaoMutuo2);

        quantidadeCabos3 = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos3);
        tipoCabo3 = root.findViewById(R.id.spinCadastroMutuoTipoCabo3);
        tipoCabo3.setEnabled(false);
        nome3 = root.findViewById(R.id.textCadastroNome3);
        finalidade3 = root.findViewById(R.id.spinCadastroFinalidade3);
        finalidade3.setEnabled(false);
        ceans3 = root.findViewById(R.id.spinCadastroCeans3);
        ceans3.setEnabled(false);
        tar3 = root.findViewById(R.id.spinCadastroTar3);
        tar3.setEnabled(false);
        reservaTec3 = root.findViewById(R.id.spinCadastroReservaTec3);
        reservaTec3.setEnabled(false);
        backbone3 = root.findViewById(R.id.spinCadastroBackbone3);
        backbone3.setEnabled(false);
        placaIdentificadora3 = root.findViewById(R.id.chkCadastroPlaca3);
        descidaCabos3 = root.findViewById(R.id.chkCadastroDescidaCabos3);
        descricaoIrregularidade3 = root.findViewById(R.id.textCadastroDescricao3);
        observacaoMutuo3 = root.findViewById(R.id.textCadastroObservacaoMutuo3);

        quantidadeCabos4 = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos4);
        tipoCabo4 = root.findViewById(R.id.spinCadastroMutuoTipoCabo4);
        tipoCabo4.setEnabled(false);
        nome4 = root.findViewById(R.id.textCadastroNome4);
        finalidade4 = root.findViewById(R.id.spinCadastroFinalidade4);
        finalidade4.setEnabled(false);
        ceans4 = root.findViewById(R.id.spinCadastroCeans4);
        ceans4.setEnabled(false);
        tar4 = root.findViewById(R.id.spinCadastroTar4);
        tar4.setEnabled(false);
        reservaTec4 = root.findViewById(R.id.spinCadastroReservaTec4);
        reservaTec4.setEnabled(false);
        backbone4 = root.findViewById(R.id.spinCadastroBackbone4);
        backbone4.setEnabled(false);
        placaIdentificadora4 = root.findViewById(R.id.chkCadastroPlaca4);
        descidaCabos4 = root.findViewById(R.id.chkCadastroDescidaCabos4);
        descricaoIrregularidade4 = root.findViewById(R.id.textCadastroDescricao4);
        observacaoMutuo4 = root.findViewById(R.id.textCadastroObservacaoMutuo4);

        quantidadeCabos5 = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos5);
        tipoCabo5 = root.findViewById(R.id.spinCadastroMutuoTipoCabo5);
        tipoCabo5.setEnabled(false);
        nome5 = root.findViewById(R.id.textCadastroNome5);
        finalidade5 = root.findViewById(R.id.spinCadastroFinalidade5);
        finalidade5.setEnabled(false);
        ceans5 = root.findViewById(R.id.spinCadastroCeans5);
        ceans5.setEnabled(false);
        tar5 = root.findViewById(R.id.spinCadastroTar5);
        tar5.setEnabled(false);
        reservaTec5 = root.findViewById(R.id.spinCadastroReservaTec5);
        reservaTec5.setEnabled(false);
        backbone5 = root.findViewById(R.id.spinCadastroBackbone5);
        backbone5.setEnabled(false);
        placaIdentificadora5 = root.findViewById(R.id.chkCadastroPlaca5);
        descidaCabos5 = root.findViewById(R.id.chkCadastroDescidaCabos5);
        descricaoIrregularidade5 = root.findViewById(R.id.textCadastroDescricao5);
        observacaoMutuo5 = root.findViewById(R.id.textCadastroObservacaoMutuo5);


        //VEGETAÇÃO
        dimensaoVegetacao = root.findViewById(R.id.spinCadastroDimensaoVegetacao);
        distaciaBaixa = root.findViewById(R.id.spinCadastroBaixa);
        distanciaMedia = root.findViewById(R.id.spinCadastroMedia);
        estadoArvore = root.findViewById(R.id.spinCadastroEstadoArvore);
        quedaArvore = root.findViewById(R.id.chkCadastroQuedaArvore);
        localArvore = root.findViewById(R.id.spinCadastroLocalArvore);
        observacaoVegetacao = root.findViewById(R.id.textCadastroObservacaoVegetacao);
        try {
            assert this.getArguments() != null;
            codigoEnergisa = (String) this.getArguments().getSerializable("codigoEnergisa");
            if (codigoEnergisa != null) {
                codigo.setText(codigoEnergisa);
            }
        }
        catch (Exception e){

        }
        Button buttonCadastrar = root.findViewById(R.id.btnCadastroSalvar);
        try {
            assert this.getArguments() != null;
            formularioAtual = (Formulario) this.getArguments().getSerializable("formularioSelecionado");
            if(formularioAtual != null){
                //LOCALIZAÇÃO
                controle = true;
                codigo.setText(formularioAtual.getCodigo());
                imgPath = formularioAtual.getCaminhoImagem();
                foto.setImageBitmap(BitmapFactory.decodeFile(imgPath));
                imagem = BitmapFactory.decodeFile(imgPath);
                imgPath2 = formularioAtual.getCaminhoImagem2();
                foto2.setImageBitmap(BitmapFactory.decodeFile(imgPath2));
                imagem2 = BitmapFactory.decodeFile(imgPath2);
                imgPath3 = formularioAtual.getCaminhoImagem3();
                foto3.setImageBitmap(BitmapFactory.decodeFile(imgPath3));
                imagem3 = BitmapFactory.decodeFile(imgPath3);
                endereco.setText(formularioAtual.getEndereco());
                urlFoto = Uri.parse(formularioAtual.getUrlImagem());
                Log.i("TAG", urlFoto.toString());
                urlFoto2 = Uri.parse(formularioAtual.getUrlImagem2());
                urlFoto3 = Uri.parse(formularioAtual.getUrlImagem3());
                if (formularioAtual.getMunicipio().equals("-")) {
                    municipio.setSelection(0);
                }else {
                    for (int i = 0; i < municipio.getAdapter().getCount(); i++) {
                        municipio.setSelection(i);
                        if (municipio.getSelectedItem().toString().equals(formularioAtual.getMunicipio())) {
                            break;
                        }
                    }
                }
                latitude.setText(formularioAtual.getLatitude());
                longitude.setText(formularioAtual.getLongitude());
                if (formularioAtual.getAlturaCarga().equals("-")) {
                    alturaCarga.setSelection(0);
                }else {
                    for (int i = 0; i < alturaCarga.getAdapter().getCount(); i++) {
                        alturaCarga.setSelection(i);
                        if (alturaCarga.getSelectedItem().toString().equals(formularioAtual.getAlturaCarga())) {
                            break;
                        }
                    }
                }


                //CARACTERISTICAS FÍSICAS

                if (formularioAtual.getTipoPoste().equals("-")) {
                    tipoPoste.setSelection(0);
                }else {
                    for (int i = 0; i < tipoPoste.getAdapter().getCount(); i++) {
                        tipoPoste.setSelection(i);
                        if (tipoPoste.getSelectedItem().toString().equals(formularioAtual.getTipoPoste())) {
                            break;
                        }
                    }
                }
                if(formularioAtual.getNormal().equals("Sim")){
                    normal.setChecked(true);
                }else{
                    normal.setChecked(false);
                    ferragemExposta.setEnabled(true);
                    fletido.setEnabled(true);
                    abalroado.setEnabled(true);
                    trincado.setEnabled(true);
                    danificado.setEnabled(true);
                }
                if(formularioAtual.getFerragemExposta().equals("Sim")){
                    ferragemExposta.setChecked(true);
                }
                if(formularioAtual.getFletido().equals("Sim")){
                    fletido.setChecked(true);
                }
                if(formularioAtual.getDanificado().equals("Sim")){
                    danificado.setChecked(true);
                }
                if(formularioAtual.getAbalroado().equals("Sim")){
                    abalroado.setChecked(true);
                }
                if(formularioAtual.getTrincado().equals("Sim")){
                    trincado.setChecked(true);
                }
                observacaoFisicas.setText(formularioAtual.getObservacaoFisicas());


                //ILUMINAÇÃO

                if(formularioAtual.getIp().equals("Sim")){
                    ip.setChecked(true);
                    ipEstrutura.setVisibility(View.VISIBLE);
                    tipoPot.setVisibility(View.VISIBLE);
                    potReator.setVisibility(View.VISIBLE);
                    quantidadeLampada.setVisibility(View.VISIBLE);
                    ipAtivacao.setVisibility(View.VISIBLE);
                    vinteEQuatro.setVisibility(View.VISIBLE);
                    quantidade24H.setVisibility(View.VISIBLE);
                    ip2.setVisibility(View.VISIBLE);
                    if (formularioAtual.getIpEstrutura().equals("-")) {
                        ipEstrutura.setSelection(0);
                    }else {
                        for (int i = 0; i < ipEstrutura.getAdapter().getCount(); i++) {
                            ipEstrutura.setSelection(i);
                            if (ipEstrutura.getSelectedItem().toString().equals(formularioAtual.getIpEstrutura())) {
                                break;
                            }
                        }
                    }
                    quantidadeLampada.setText(formularioAtual.getQuantidadeLampada());
                    if (formularioAtual.getTipoPot().equals("-")) {
                        tipoPot.setSelection(0);
                    }else {
                        for (int i = 0; i < tipoPot.getAdapter().getCount(); i++) {
                            tipoPot.setSelection(i);
                            if (tipoPot.getSelectedItem().toString().equals(formularioAtual.getTipoPot())) {
                                break;
                            }
                        }
                    }
                    potReator.setText(formularioAtual.getPotReator());
                    if (formularioAtual.getIpAtivacao().equals("-")) {
                        ipAtivacao.setSelection(0);
                    }else {
                        for (int i = 0; i < ipAtivacao.getAdapter().getCount(); i++) {
                            ipAtivacao.setSelection(i);
                            if (ipAtivacao.getSelectedItem().toString().equals(formularioAtual.getIpAtivacao())) {
                                break;
                            }
                        }
                    }
                    if(formularioAtual.getVinteEQuatro().equals("Sim")){
                        vinteEQuatro.setChecked(true);
                        quantidade24H.setEnabled(true);
                    }
                    quantidade24H.setText(formularioAtual.getQuantidade24H());
                    ipEstrutura.setEnabled(true);
                    quantidadeLampada.setEnabled(true);
                    tipoPot.setEnabled(true);
                    potReator.setEnabled(true);
                    ipAtivacao.setEnabled(true);
                    vinteEQuatro.setEnabled(true);
                    ip2.setEnabled(true);
                    if(formularioAtual.getIp2().equals("Sim")){
                        ip2.setChecked(true);
                        ipEstrutura2.setVisibility(View.VISIBLE);
                        tipoPot2.setVisibility(View.VISIBLE);
                        potReator2.setVisibility(View.VISIBLE);
                        quantidadeLampada2.setVisibility(View.VISIBLE);
                        ipAtivacao2.setVisibility(View.VISIBLE);
                        vinteEQuatro2.setVisibility(View.VISIBLE);
                        quantidade24H2.setVisibility(View.VISIBLE);
                        ip3.setVisibility(View.VISIBLE);
                        if (formularioAtual.getIpEstrutura2().equals("-")) {
                            ipEstrutura2.setSelection(0);
                        }else {
                            for (int i = 0; i < ipEstrutura2.getAdapter().getCount(); i++) {
                                ipEstrutura2.setSelection(i);
                                if (ipEstrutura2.getSelectedItem().toString().equals(formularioAtual.getIpEstrutura2())) {
                                    break;
                                }
                            }
                        }
                        quantidadeLampada2.setText(formularioAtual.getQuantidadeLampada2());
                        if (formularioAtual.getTipoPot2().equals("-")) {
                            tipoPot2.setSelection(0);
                        }else {
                            for (int i = 0; i < tipoPot2.getAdapter().getCount(); i++) {
                                tipoPot2.setSelection(i);
                                if (tipoPot2.getSelectedItem().toString().equals(formularioAtual.getTipoPot2())) {
                                    break;
                                }
                            }
                        }
                        potReator2.setText(formularioAtual.getPotReator2());
                        if (formularioAtual.getIpAtivacao2().equals("-")) {
                            ipAtivacao2.setSelection(0);
                        }else {
                            for (int i = 0; i < ipAtivacao2.getAdapter().getCount(); i++) {
                                ipAtivacao2.setSelection(i);
                                if (ipAtivacao2.getSelectedItem().toString().equals(formularioAtual.getIpAtivacao2())) {
                                    break;
                                }
                            }
                        }
                        if(formularioAtual.getVinteEQuatro2().equals("Sim")){
                            vinteEQuatro2.setChecked(true);
                            quantidade24H2.setEnabled(true);
                        }
                        quantidade24H2.setText(formularioAtual.getQuantidade24H2());
                        ipEstrutura2.setEnabled(true);
                        quantidadeLampada2.setEnabled(true);
                        tipoPot2.setEnabled(true);
                        potReator2.setEnabled(true);
                        ipAtivacao2.setEnabled(true);
                        vinteEQuatro2.setEnabled(true);
                        ip3.setEnabled(true);
                        if(formularioAtual.getIp3().equals("Sim")){
                            ip3.setChecked(true);
                            ipEstrutura3.setVisibility(View.VISIBLE);
                            tipoPot3.setVisibility(View.VISIBLE);
                            potReator3.setVisibility(View.VISIBLE);
                            quantidadeLampada3.setVisibility(View.VISIBLE);
                            ipAtivacao3.setVisibility(View.VISIBLE);
                            vinteEQuatro3.setVisibility(View.VISIBLE);
                            quantidade24H3.setVisibility(View.VISIBLE);
                            if (formularioAtual.getIpEstrutura3().equals("-")) {
                                ipEstrutura3.setSelection(0);
                            }else {
                                for (int i = 0; i < ipEstrutura3.getAdapter().getCount(); i++) {
                                    ipEstrutura3.setSelection(i);
                                    if (ipEstrutura3.getSelectedItem().toString().equals(formularioAtual.getIpEstrutura3())) {
                                        break;
                                    }
                                }
                            }
                            quantidadeLampada3.setText(formularioAtual.getQuantidadeLampada3());
                            if (formularioAtual.getTipoPot3().equals("-")) {
                                tipoPot3.setSelection(0);
                            }else {
                                for (int i = 0; i < tipoPot3.getAdapter().getCount(); i++) {
                                    tipoPot3.setSelection(i);
                                    if (tipoPot3.getSelectedItem().toString().equals(formularioAtual.getTipoPot3())) {
                                        break;
                                    }
                                }
                            }
                            potReator3.setText(formularioAtual.getPotReator3());
                            if (formularioAtual.getIpAtivacao3().equals("-")) {
                                ipAtivacao3.setSelection(0);
                            }else {
                                for (int i = 0; i < ipAtivacao3.getAdapter().getCount(); i++) {
                                    ipAtivacao3.setSelection(i);
                                    if (ipAtivacao3.getSelectedItem().toString().equals(formularioAtual.getIpAtivacao3())) {
                                        break;
                                    }
                                }
                            }
                            if(formularioAtual.getVinteEQuatro3().equals("Sim")){
                                vinteEQuatro3.setChecked(true);
                                quantidade24H3.setEnabled(true);
                            }
                            quantidade24H3.setText(formularioAtual.getQuantidade24H3());
                            ipEstrutura3.setEnabled(true);
                            quantidadeLampada3.setEnabled(true);
                            tipoPot3.setEnabled(true);
                            potReator3.setEnabled(true);
                            ipAtivacao3.setEnabled(true);
                            vinteEQuatro3.setEnabled(true);

                        }

                    }


                }
                observacaoIP.setText(formularioAtual.getObservacaoIP());


                //TRAFO

                if(formularioAtual.getAtivos().equals("Sim")){
                    ativos.setChecked(true);
                    chkTrafoMono.setEnabled(true);
                    chkTrafoTrifasico.setEnabled(true);
                    trafoMono.setEnabled(true);
                    trafoTrifasico.setEnabled(true);
                    chFusivel.setEnabled(true);
                    chFaca.setEnabled(true);
                    religador.setEnabled(true);
                    medicao.setEnabled(true);
                    chBanco.setEnabled(true);
                    chFusivelReligador.setEnabled(true);
                    ramalSubt.setEnabled(true);
                    outros.setEnabled(true);

                    ativos.setVisibility(View.VISIBLE);
                    chkTrafoMono.setVisibility(View.VISIBLE);
                    chkTrafoTrifasico.setVisibility(View.VISIBLE);
                    trafoMono.setVisibility(View.VISIBLE);
                    trafoTrifasico.setVisibility(View.VISIBLE);
                    chFusivel.setVisibility(View.VISIBLE);
                    chFaca.setVisibility(View.VISIBLE);
                    religador.setVisibility(View.VISIBLE);
                    medicao.setVisibility(View.VISIBLE);
                    chBanco.setVisibility(View.VISIBLE);
                    chFusivelReligador.setVisibility(View.VISIBLE);
                    ramalSubt.setVisibility(View.VISIBLE);
                    outros.setVisibility(View.VISIBLE);
                }
                if(formularioAtual.getChkTrafoTrifasico().equals("Sim")){
                    chkTrafoTrifasico.setChecked(true);
                }
                if(formularioAtual.getChkTrafoMono().equals("Sim")){
                    chkTrafoMono.setChecked(true);
                }
                if (formularioAtual.getTrafoTrifasico().equals("-")) {
                    trafoTrifasico.setSelection(0);
                }else {
                    for (int i = 0; i < trafoTrifasico.getAdapter().getCount(); i++) {
                        trafoTrifasico.setSelection(i);
                        if (trafoTrifasico.getSelectedItem().toString().equals(formularioAtual.getTrafoTrifasico())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getTrafoMono().equals("-")) {
                    trafoMono.setSelection(0);
                }else {
                    for (int i = 0; i < trafoMono.getAdapter().getCount(); i++) {
                        trafoMono.setSelection(i);
                        if (trafoMono.getSelectedItem().toString().equals(formularioAtual.getTrafoMono())) {
                            break;
                        }
                    }
                }

                if(formularioAtual.getReligador().equals("Sim")){
                    religador.setChecked(true);
                }
                if(formularioAtual.getMedicao().equals("Sim")){
                    medicao.setChecked(true);
                }
                if(formularioAtual.getChFusivel().equals("Sim")){
                    chFusivel.setChecked(true);
                }
                if(formularioAtual.getChFaca().equals("Sim")){
                    chFaca.setChecked(true);
                }
                if(formularioAtual.getBanco().equals("Sim")){
                    chBanco.setChecked(true);
                }
                if(formularioAtual.getChFusivelReligador().equals("Sim")){
                    chFusivelReligador.setChecked(true);
                }
                if (formularioAtual.getRamalSubt().equals("-")) {
                    ramalSubt.setSelection(0);
                }else {
                    for (int i = 0; i < ramalSubt.getAdapter().getCount(); i++) {
                        ramalSubt.setSelection(i);
                        if (ramalSubt.getSelectedItem().toString().equals(formularioAtual.getRamalSubt())) {
                            break;
                        }
                    }
                }
                observacaoAtivos.setText(formularioAtual.getObservacaoAtivos());
                outros.setText(formularioAtual.getOutros());

                //MUTUO
               if (formularioAtual.getQuantidadeOcupantes().equals("-")) {
                    quantidadeOcupantes.setSelection(0);
                }else {
                    for (int i = 0; i < quantidadeOcupantes.getAdapter().getCount(); i++) {
                        quantidadeOcupantes.setSelection(i);
                        if (quantidadeOcupantes.getSelectedItem().toString().equals(formularioAtual.getQuantidadeOcupantes())) {
                            break;
                        }
                    }
                }
                if(formularioAtual.getMutuo().equals("Sim")){
                    mutuo.setChecked(true);
                    quantidadeOcupantes.setEnabled(true);
                    if(formularioAtual.getQuantidadeOcupantes().equals("0")){
                        // region MutuoDados0
                        mutuoDados(quantidadeCabos,null,false);
                        mutuoDados(quantidadeCabos2,null,false);
                        mutuoDados(quantidadeCabos2dois,null,false);
                        mutuoDados(quantidadeCabos3,null,false);
                        mutuoDados(quantidadeCabos4,null,false);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,false);
                        mutuoDados(null,tipoCabo2dois,false);
                        mutuoDados(null,tipoCabo3,false);
                        mutuoDados(null,tipoCabo4,false);
                        mutuoDados(null,tipoCabo5,false);

                        nome.setVisibility(View.GONE);
                        nome2.setVisibility(View.GONE);
                        mutuoDados(nome3,null,false);
                        mutuoDados(nome4,null,false);
                        mutuoDados(nome5,null,false);

                        mutuoDados(null,finalidade,false);
                        mutuoDados(null,finalidade2,false);
                        mutuoDados(null,finalidade3,false);
                        mutuoDados(null,finalidade4,false);
                        mutuoDados(null,finalidade5,false);

                        mutuoDados(null,ceans,false);
                        mutuoDados(null,ceans2,false);
                        mutuoDados(null,ceans3,false);
                        mutuoDados(null,ceans4,false);
                        mutuoDados(null,ceans5,false);

                        mutuoDados(null,tar,false);
                        mutuoDados(null,tar2,false);
                        mutuoDados(null,tar3,false);
                        mutuoDados(null,tar4,false);
                        mutuoDados(null,tar5,false);

                        mutuoDados(null,reservaTec,false);
                        mutuoDados(null,reservaTec2,false);
                        mutuoDados(null,reservaTec3,false);
                        mutuoDados(null,reservaTec4,false);
                        mutuoDados(null,reservaTec5,false);

                        mutuoDados(null,backbone,false);
                        mutuoDados(null,backbone2,false);
                        mutuoDados(null,backbone3,false);
                        mutuoDados(null,backbone4,false);
                        mutuoDados(null,backbone5,false);

                        mutuoDados(descricaoIrregularidade,null,false);
                        mutuoDados(descricaoIrregularidade2,null,false);
                        mutuoDados(descricaoIrregularidade3,null,false);
                        mutuoDados(descricaoIrregularidade4,null,false);
                        mutuoDados(descricaoIrregularidade5,null,false);

                        mutuoDados(observacaoMutuo,null,false);
                        mutuoDados(observacaoMutuo2,null,false);
                        mutuoDados(observacaoMutuo3,null,false);
                        mutuoDados(observacaoMutuo4,null,false);
                        mutuoDados(observacaoMutuo5,null,false);

                        placaIdentificadora.setChecked(false);
                        placaIdentificadora.setEnabled(false);
                        placaIdentificadora2.setChecked(false);
                        placaIdentificadora2.setEnabled(false);
                        placaIdentificadora3.setChecked(false);
                        placaIdentificadora3.setEnabled(false);
                        placaIdentificadora4.setChecked(false);
                        placaIdentificadora4.setEnabled(false);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);

                        descidaCabos.setChecked(false);
                        descidaCabos.setEnabled(false);
                        descidaCabos2.setChecked(false);
                        descidaCabos2.setEnabled(false);
                        descidaCabos3.setChecked(false);
                        descidaCabos3.setEnabled(false);
                        descidaCabos4.setChecked(false);
                        descidaCabos4.setEnabled(false);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        mutuo2.setVisibility(View.GONE);
                        mutuo3.setVisibility(View.GONE);
                        mutuo4.setVisibility(View.GONE);
                        mutuo5.setVisibility(View.GONE);
                        // endregion
                    }
                    else if(formularioAtual.getQuantidadeOcupantes().equals("1")){
                        Log.i("Spinenr","Apertei 1");
                        // region MutuoDados1
                        mutuoDados(quantidadeCabos,null,true);
                        mutuoDados(quantidadeCabos2,null,false);
                        mutuoDados(quantidadeCabos2dois,null,false);
                        mutuoDados(quantidadeCabos3,null,false);
                        mutuoDados(quantidadeCabos4,null,false);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,false);
                        mutuoDados(null,tipoCabo2dois,false);
                        mutuoDados(null,tipoCabo3,false);
                        mutuoDados(null,tipoCabo4,false);
                        mutuoDados(null,tipoCabo5,false);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.GONE);
                        mutuoDados(nome3,null,false);
                        mutuoDados(nome4,null,false);
                        mutuoDados(nome5,null,false);

                        mutuoDados(null,finalidade,true);
                        mutuoDados(null,finalidade2,false);
                        mutuoDados(null,finalidade3,false);
                        mutuoDados(null,finalidade4,false);
                        mutuoDados(null,finalidade5,false);

                        mutuoDados(null,ceans,true);
                        mutuoDados(null,ceans2,false);
                        mutuoDados(null,ceans3,false);
                        mutuoDados(null,ceans4,false);
                        mutuoDados(null,ceans5,false);

                        mutuoDados(null,tar,true);
                        mutuoDados(null,tar2,false);
                        mutuoDados(null,tar3,false);
                        mutuoDados(null,tar4,false);
                        mutuoDados(null,tar5,false);

                        mutuoDados(null,reservaTec,true);
                        mutuoDados(null,reservaTec2,false);
                        mutuoDados(null,reservaTec3,false);
                        mutuoDados(null,reservaTec4,false);
                        mutuoDados(null,reservaTec5,false);

                        mutuoDados(null,backbone,true);
                        mutuoDados(null,backbone2,false);
                        mutuoDados(null,backbone3,false);
                        mutuoDados(null,backbone4,false);
                        mutuoDados(null,backbone5,false);

                        mutuoDados(descricaoIrregularidade,null,true);
                        mutuoDados(descricaoIrregularidade2,null,false);
                        mutuoDados(descricaoIrregularidade3,null,false);
                        mutuoDados(descricaoIrregularidade4,null,false);
                        mutuoDados(descricaoIrregularidade5,null,false);

                        mutuoDados(observacaoMutuo,null,true);
                        mutuoDados(observacaoMutuo2,null,false);
                        mutuoDados(observacaoMutuo3,null,false);
                        mutuoDados(observacaoMutuo4,null,false);
                        mutuoDados(observacaoMutuo5,null,false);


                        placaIdentificadora.setEnabled(true);
                        placaIdentificadora2.setChecked(false);
                        placaIdentificadora2.setEnabled(false);
                        placaIdentificadora3.setChecked(false);
                        placaIdentificadora3.setEnabled(false);
                        placaIdentificadora4.setChecked(false);
                        placaIdentificadora4.setEnabled(false);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);


                        descidaCabos.setEnabled(true);
                        descidaCabos2.setChecked(false);
                        descidaCabos2.setEnabled(false);
                        descidaCabos3.setChecked(false);
                        descidaCabos3.setEnabled(false);
                        descidaCabos4.setChecked(false);
                        descidaCabos4.setEnabled(false);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        mutuo2.setVisibility(View.GONE);
                        mutuo3.setVisibility(View.GONE);
                        mutuo4.setVisibility(View.GONE);
                        mutuo5.setVisibility(View.GONE);

                        //endregion
                    }
                    else if(formularioAtual.getQuantidadeOcupantes().equals("2")){
                        Log.i("Spinenr","Apertei 2");
                        //region MutuoDados2

                        mutuoDados(quantidadeCabos,null,true);
                        mutuoDados(quantidadeCabos2,null,true);
                        mutuoDados(quantidadeCabos2dois,null,true);
                        mutuoDados(quantidadeCabos3,null,false);
                        mutuoDados(quantidadeCabos4,null,false);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,true);
                        mutuoDados(null,tipoCabo2dois,true);
                        mutuoDados(null,tipoCabo3,false);
                        mutuoDados(null,tipoCabo4,false);
                        mutuoDados(null,tipoCabo5,false);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.VISIBLE);
                        mutuoDados(nome3,null,false);
                        mutuoDados(nome4,null,false);
                        mutuoDados(nome5,null,false);

                        mutuoDados(null,finalidade,true);
                        mutuoDados(null,finalidade2,true);
                        mutuoDados(null,finalidade3,false);
                        mutuoDados(null,finalidade4,false);
                        mutuoDados(null,finalidade5,false);

                        mutuoDados(null,ceans,true);
                        mutuoDados(null,ceans2,true);
                        mutuoDados(null,ceans3,false);
                        mutuoDados(null,ceans4,false);
                        mutuoDados(null,ceans5,false);

                        mutuoDados(null,tar,true);
                        mutuoDados(null,tar2,true);
                        mutuoDados(null,tar3,false);
                        mutuoDados(null,tar4,false);
                        mutuoDados(null,tar5,false);

                        mutuoDados(null,reservaTec,true);
                        mutuoDados(null,reservaTec2,true);
                        mutuoDados(null,reservaTec3,false);
                        mutuoDados(null,reservaTec4,false);
                        mutuoDados(null,reservaTec5,false);

                        mutuoDados(null,backbone,true);
                        mutuoDados(null,backbone2,true);
                        mutuoDados(null,backbone3,false);
                        mutuoDados(null,backbone4,false);
                        mutuoDados(null,backbone5,false);

                        mutuoDados(descricaoIrregularidade,null,true);
                        mutuoDados(descricaoIrregularidade2,null,true);
                        mutuoDados(descricaoIrregularidade3,null,false);
                        mutuoDados(descricaoIrregularidade4,null,false);
                        mutuoDados(descricaoIrregularidade5,null,false);

                        mutuoDados(observacaoMutuo,null,true);
                        mutuoDados(observacaoMutuo2,null,true);
                        mutuoDados(observacaoMutuo3,null,false);
                        mutuoDados(observacaoMutuo4,null,false);
                        mutuoDados(observacaoMutuo5,null,false);


                        placaIdentificadora.setEnabled(true);

                        placaIdentificadora2.setEnabled(true);
                        placaIdentificadora3.setChecked(false);
                        placaIdentificadora3.setEnabled(false);
                        placaIdentificadora4.setChecked(false);
                        placaIdentificadora4.setEnabled(false);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);


                        descidaCabos.setEnabled(true);

                        descidaCabos2.setEnabled(true);
                        descidaCabos3.setChecked(false);
                        descidaCabos3.setEnabled(false);
                        descidaCabos4.setChecked(false);
                        descidaCabos4.setEnabled(false);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        mutuo2.setVisibility(View.VISIBLE);
                        mutuo3.setVisibility(View.GONE);
                        mutuo4.setVisibility(View.GONE);
                        mutuo5.setVisibility(View.GONE);

                        //endregion
                    }
                    else if(formularioAtual.getQuantidadeOcupantes().equals("3")){
                        Log.i("Spinenr","Apertei 3");
                        //region MutuoDados3

                        mutuoDados(quantidadeCabos,null,true);
                        mutuoDados(quantidadeCabos2,null,true);
                        mutuoDados(quantidadeCabos2dois,null,true);
                        mutuoDados(quantidadeCabos3,null,true);
                        mutuoDados(quantidadeCabos4,null,false);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,true);
                        mutuoDados(null,tipoCabo2dois,true);
                        mutuoDados(null,tipoCabo3,true);
                        mutuoDados(null,tipoCabo4,false);
                        mutuoDados(null,tipoCabo5,false);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.VISIBLE);
                        mutuoDados(nome3,null,true);
                        mutuoDados(nome4,null,false);
                        mutuoDados(nome5,null,false);

                        mutuoDados(null,finalidade,true);
                        mutuoDados(null,finalidade2,true);
                        mutuoDados(null,finalidade3,true);
                        mutuoDados(null,finalidade4,false);
                        mutuoDados(null,finalidade5,false);

                        mutuoDados(null,ceans,true);
                        mutuoDados(null,ceans2,true);
                        mutuoDados(null,ceans3,true);
                        mutuoDados(null,ceans4,false);
                        mutuoDados(null,ceans5,false);

                        mutuoDados(null,tar,true);
                        mutuoDados(null,tar2,true);
                        mutuoDados(null,tar3,true);
                        mutuoDados(null,tar4,false);
                        mutuoDados(null,tar5,false);

                        mutuoDados(null,reservaTec,true);
                        mutuoDados(null,reservaTec2,true);
                        mutuoDados(null,reservaTec3,true);
                        mutuoDados(null,reservaTec4,false);
                        mutuoDados(null,reservaTec5,false);

                        mutuoDados(null,backbone,true);
                        mutuoDados(null,backbone2,true);
                        mutuoDados(null,backbone3,true);
                        mutuoDados(null,backbone4,false);
                        mutuoDados(null,backbone5,false);

                        mutuoDados(descricaoIrregularidade,null,true);
                        mutuoDados(descricaoIrregularidade2,null,true);
                        mutuoDados(descricaoIrregularidade3,null,true);
                        mutuoDados(descricaoIrregularidade4,null,false);
                        mutuoDados(descricaoIrregularidade5,null,false);

                        mutuoDados(observacaoMutuo,null,true);
                        mutuoDados(observacaoMutuo2,null,true);
                        mutuoDados(observacaoMutuo3,null,true);
                        mutuoDados(observacaoMutuo4,null,false);
                        mutuoDados(observacaoMutuo5,null,false);


                        placaIdentificadora.setEnabled(true);

                        placaIdentificadora2.setEnabled(true);

                        placaIdentificadora3.setEnabled(true);
                        placaIdentificadora4.setChecked(false);
                        placaIdentificadora4.setEnabled(false);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);


                        descidaCabos.setEnabled(true);

                        descidaCabos2.setEnabled(true);

                        descidaCabos3.setEnabled(true);
                        descidaCabos4.setChecked(false);
                        descidaCabos4.setEnabled(false);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        mutuo2.setVisibility(View.VISIBLE);
                        mutuo3.setVisibility(View.VISIBLE);
                        mutuo4.setVisibility(View.GONE);
                        mutuo5.setVisibility(View.GONE);

                        //endregion
                    }
                    else if(formularioAtual.getQuantidadeOcupantes().equals("4")){
                        Log.i("Spinenr","Apertei 4");
                        //region MutuoDados4

                        mutuoDados(quantidadeCabos,null,true);
                        mutuoDados(quantidadeCabos2,null,true);
                        mutuoDados(quantidadeCabos2dois,null,true);
                        mutuoDados(quantidadeCabos3,null,true);
                        mutuoDados(quantidadeCabos4,null,true);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,true);
                        mutuoDados(null,tipoCabo2dois,true);
                        mutuoDados(null,tipoCabo3,true);
                        mutuoDados(null,tipoCabo4,true);
                        mutuoDados(null,tipoCabo5,false);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.VISIBLE);
                        mutuoDados(nome3,null,true);
                        mutuoDados(nome4,null,true);
                        mutuoDados(nome5,null,false);

                        mutuoDados(null,finalidade,true);
                        mutuoDados(null,finalidade2,true);
                        mutuoDados(null,finalidade3,true);
                        mutuoDados(null,finalidade4,true);
                        mutuoDados(null,finalidade5,false);

                        mutuoDados(null,ceans,true);
                        mutuoDados(null,ceans2,true);
                        mutuoDados(null,ceans3,true);
                        mutuoDados(null,ceans4,true);
                        mutuoDados(null,ceans5,false);

                        mutuoDados(null,tar,true);
                        mutuoDados(null,tar2,true);
                        mutuoDados(null,tar3,true);
                        mutuoDados(null,tar4,true);
                        mutuoDados(null,tar5,false);

                        mutuoDados(null,reservaTec,true);
                        mutuoDados(null,reservaTec2,true);
                        mutuoDados(null,reservaTec3,true);
                        mutuoDados(null,reservaTec4,true);
                        mutuoDados(null,reservaTec5,false);

                        mutuoDados(null,backbone,true);
                        mutuoDados(null,backbone2,true);
                        mutuoDados(null,backbone3,true);
                        mutuoDados(null,backbone4,true);
                        mutuoDados(null,backbone5,false);

                        mutuoDados(descricaoIrregularidade,null,true);
                        mutuoDados(descricaoIrregularidade2,null,true);
                        mutuoDados(descricaoIrregularidade3,null,true);
                        mutuoDados(descricaoIrregularidade4,null,true);
                        mutuoDados(descricaoIrregularidade5,null,false);

                        mutuoDados(observacaoMutuo,null,true);
                        mutuoDados(observacaoMutuo2,null,true);
                        mutuoDados(observacaoMutuo3,null,true);
                        mutuoDados(observacaoMutuo4,null,true);
                        mutuoDados(observacaoMutuo5,null,false);


                        placaIdentificadora.setEnabled(true);

                        placaIdentificadora2.setEnabled(true);

                        placaIdentificadora3.setEnabled(true);

                        placaIdentificadora4.setEnabled(true);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);


                        descidaCabos.setEnabled(true);

                        descidaCabos2.setEnabled(true);

                        descidaCabos3.setEnabled(true);

                        descidaCabos4.setEnabled(true);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        mutuo2.setVisibility(View.VISIBLE);
                        mutuo3.setVisibility(View.VISIBLE);
                        mutuo4.setVisibility(View.VISIBLE);
                        mutuo5.setVisibility(View.GONE);
                        //endregion
                    }
                    else if(formularioAtual.getQuantidadeOcupantes().equals("5")){
                        Log.i("Spinenr","Apertei 5");
                        //region MutuoDados5

                        mutuoDados(quantidadeCabos,null,true);
                        mutuoDados(quantidadeCabos2,null,true);
                        mutuoDados(quantidadeCabos2dois,null,true);
                        mutuoDados(quantidadeCabos3,null,true);
                        mutuoDados(quantidadeCabos4,null,true);
                        mutuoDados(quantidadeCabos5,null,true);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,true);
                        mutuoDados(null,tipoCabo2dois,true);
                        mutuoDados(null,tipoCabo3,true);
                        mutuoDados(null,tipoCabo4,true);
                        mutuoDados(null,tipoCabo5,true);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.VISIBLE);
                        mutuoDados(nome3,null,true);
                        mutuoDados(nome4,null,true);
                        mutuoDados(nome5,null,true);

                        mutuoDados(null,finalidade,true);
                        mutuoDados(null,finalidade2,true);
                        mutuoDados(null,finalidade3,true);
                        mutuoDados(null,finalidade4,true);
                        mutuoDados(null,finalidade5,true);

                        mutuoDados(null,ceans,true);
                        mutuoDados(null,ceans2,true);
                        mutuoDados(null,ceans3,true);
                        mutuoDados(null,ceans4,true);
                        mutuoDados(null,ceans5,true);

                        mutuoDados(null,tar,true);
                        mutuoDados(null,tar2,true);
                        mutuoDados(null,tar3,true);
                        mutuoDados(null,tar4,true);
                        mutuoDados(null,tar5,true);

                        mutuoDados(null,reservaTec,true);
                        mutuoDados(null,reservaTec2,true);
                        mutuoDados(null,reservaTec3,true);
                        mutuoDados(null,reservaTec4,true);
                        mutuoDados(null,reservaTec5,true);

                        mutuoDados(null,backbone,true);
                        mutuoDados(null,backbone2,true);
                        mutuoDados(null,backbone3,true);
                        mutuoDados(null,backbone4,true);
                        mutuoDados(null,backbone5,true);

                        mutuoDados(descricaoIrregularidade,null,true);
                        mutuoDados(descricaoIrregularidade2,null,true);
                        mutuoDados(descricaoIrregularidade3,null,true);
                        mutuoDados(descricaoIrregularidade4,null,true);
                        mutuoDados(descricaoIrregularidade5,null,true);

                        mutuoDados(observacaoMutuo,null,true);
                        mutuoDados(observacaoMutuo2,null,true);
                        mutuoDados(observacaoMutuo3,null,true);
                        mutuoDados(observacaoMutuo4,null,true);
                        mutuoDados(observacaoMutuo5,null,true);


                        placaIdentificadora.setEnabled(true);

                        placaIdentificadora2.setEnabled(true);

                        placaIdentificadora3.setEnabled(true);

                        placaIdentificadora4.setEnabled(true);

                        placaIdentificadora5.setEnabled(true);


                        descidaCabos.setEnabled(true);

                        descidaCabos2.setEnabled(true);

                        descidaCabos3.setEnabled(true);

                        descidaCabos4.setEnabled(true);

                        descidaCabos5.setEnabled(true);

                        mutuo2.setVisibility(View.VISIBLE);
                        mutuo3.setVisibility(View.VISIBLE);
                        mutuo4.setVisibility(View.VISIBLE);
                        mutuo5.setVisibility(View.VISIBLE);

                        //endregion
                    }




                }
                //1
                quantidadeCabos.setText(formularioAtual.getQuantidadeCabos());
                if (formularioAtual.getTipoCabo().equals("-")) {
                    tipoCabo.setSelection(0);
                }else {
                    for (int i = 0; i < tipoCabo.getAdapter().getCount(); i++) {
                        tipoCabo.setSelection(i);
                        if (tipoCabo.getSelectedItem().toString().equals(formularioAtual.getTipoCabo())) {
                            break;
                        }
                    }
                }
                nome.setText(formularioAtual.getNome());
                if (formularioAtual.getFinalidade().equals("-")) {
                    finalidade.setSelection(0);
                }else {
                    for (int i = 0; i < finalidade.getAdapter().getCount(); i++) {
                        finalidade.setSelection(i);
                        if (finalidade.getSelectedItem().toString().equals(formularioAtual.getFinalidade())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getCeans().equals("-")) {
                    ceans.setSelection(0);
                }else {
                    for (int i = 0; i < ceans.getAdapter().getCount(); i++) {
                        ceans.setSelection(i);
                        if (ceans.getSelectedItem().toString().equals(formularioAtual.getCeans())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getTar().equals("-")) {
                    tar.setSelection(0);
                }else {
                    for (int i = 0; i < tar.getAdapter().getCount(); i++) {
                        tar.setSelection(i);
                        if (tar.getSelectedItem().toString().equals(formularioAtual.getTar())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getReservaTec().equals("-")) {
                    reservaTec.setSelection(0);
                }else {
                    for (int i = 0; i < reservaTec.getAdapter().getCount(); i++) {
                        reservaTec.setSelection(i);
                        if (reservaTec.getSelectedItem().toString().equals(formularioAtual.getReservaTec())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getBackbone().equals("-")) {
                    backbone.setSelection(0);
                }else {
                    for (int i = 0; i < backbone.getAdapter().getCount(); i++) {
                        backbone.setSelection(i);
                        if (backbone.getSelectedItem().toString().equals(formularioAtual.getBackbone())) {
                            break;
                        }
                    }
                }
                if(formularioAtual.getPlacaIdent().equals("Sim")){
                    placaIdentificadora.setChecked(true);
                }
                if(formularioAtual.getDescidaCabos().equals("Sim")){
                    descidaCabos.setChecked(true);
                }
                descricaoIrregularidade.setText(formularioAtual.getDescricaoIrregularidade());
                observacaoMutuo.setText((formularioAtual.getObservacaoMutuo()));
                //2
                quantidadeCabos2.setText(formularioAtual.getQuantidadeCabos2());
                if (formularioAtual.getTipoCabo2().equals("-")) {
                    tipoCabo2.setSelection(0);
                }else {
                    for (int i = 0; i < tipoCabo2.getAdapter().getCount(); i++) {
                        tipoCabo2.setSelection(i);
                        if (tipoCabo2.getSelectedItem().toString().equals(formularioAtual.getTipoCabo2())) {
                            break;
                        }
                    }
                }
                quantidadeCabos2dois.setText(formularioAtual.getQuantidadeCabos2dois());
                if (formularioAtual.getTipoCabo2dois().equals("-")) {
                    tipoCabo2dois.setSelection(0);
                }else {
                    for (int i = 0; i < tipoCabo2dois.getAdapter().getCount(); i++) {
                        tipoCabo2dois.setSelection(i);
                        if (tipoCabo2dois.getSelectedItem().toString().equals(formularioAtual.getTipoCabo2dois())) {
                            break;
                        }
                    }
                }
                nome2.setText(formularioAtual.getNome2());
                if (formularioAtual.getFinalidade2().equals("-")) {
                    finalidade2.setSelection(0);
                }else {
                    for (int i = 0; i < finalidade2.getAdapter().getCount(); i++) {
                        finalidade2.setSelection(i);
                        if (finalidade2.getSelectedItem().toString().equals(formularioAtual.getFinalidade2())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getCeans2().equals("-")) {
                    ceans2.setSelection(0);
                }else {
                    for (int i = 0; i < ceans2.getAdapter().getCount(); i++) {
                        ceans2.setSelection(i);
                        if (ceans2.getSelectedItem().toString().equals(formularioAtual.getCeans2())) {
                            break;
                        }
                    }
                }
                    if (formularioAtual.getTar2().equals("-")) {
                        tar2.setSelection(0);
                    }else {
                        for (int i = 0; i < tar2.getAdapter().getCount(); i++) {
                            tar2.setSelection(i);
                            if (tar2.getSelectedItem().toString().equals(formularioAtual.getTar2())) {
                                break;
                            }
                        }
                    }
                if (formularioAtual.getReservaTec2().equals("-")) {
                    reservaTec2.setSelection(0);
                }else {
                    for (int i = 0; i < reservaTec2.getAdapter().getCount(); i++) {
                        reservaTec2.setSelection(i);
                        if (reservaTec2.getSelectedItem().toString().equals(formularioAtual.getReservaTec2())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getBackbone2().equals("-")) {
                    backbone2.setSelection(0);
                }else {
                    for (int i = 0; i < backbone2.getAdapter().getCount(); i++) {
                        backbone2.setSelection(i);
                        if (backbone2.getSelectedItem().toString().equals(formularioAtual.getBackbone2())) {
                            break;
                        }
                    }
                }
                if(formularioAtual.getPlacaIdent2().equals("Sim")){
                    placaIdentificadora2.setChecked(true);
                }
                if(formularioAtual.getDescidaCabos2().equals("Sim")){
                    descidaCabos2.setChecked(true);
                }
                descricaoIrregularidade2.setText(formularioAtual.getDescricaoIrregularidade2());
                observacaoMutuo2.setText((formularioAtual.getObservacaoMutuo2()));
                //3
                quantidadeCabos3.setText(formularioAtual.getQuantidadeCabos3());
                if (formularioAtual.getTipoCabo3().equals("-")) {
                    tipoCabo3.setSelection(0);
                }else {
                    for (int i = 0; i < tipoCabo3.getAdapter().getCount(); i++) {
                        tipoCabo3.setSelection(i);
                        if (tipoCabo3.getSelectedItem().toString().equals(formularioAtual.getTipoCabo3())) {
                            break;
                        }
                    }
                }
                nome3.setText(formularioAtual.getNome3());
                if (formularioAtual.getFinalidade3().equals("-")) {
                    finalidade3.setSelection(0);
                }else {
                    for (int i = 0; i < finalidade3.getAdapter().getCount(); i++) {
                        finalidade3.setSelection(i);
                        if (finalidade3.getSelectedItem().toString().equals(formularioAtual.getFinalidade3())) {
                            break;
                        }
                    }
                }
                    if (formularioAtual.getCeans3().equals("-")) {
                        ceans3.setSelection(0);
                    }else {
                        for (int i = 0; i < ceans3.getAdapter().getCount(); i++) {
                            ceans3.setSelection(i);
                            if (ceans3.getSelectedItem().toString().equals(formularioAtual.getCeans3())) {
                                break;
                            }
                        }
                    }
                if (formularioAtual.getTar3().equals("-")) {
                    tar3.setSelection(0);
                }else {
                    for (int i = 0; i < tar3.getAdapter().getCount(); i++) {
                        tar3.setSelection(i);
                        if (tar3.getSelectedItem().toString().equals(formularioAtual.getTar3())) {
                            break;
                        }
                    }
                }
                    if (formularioAtual.getReservaTec3().equals("-")) {
                        reservaTec3.setSelection(0);
                    }else {
                        for (int i = 0; i < reservaTec3.getAdapter().getCount(); i++) {
                            reservaTec3.setSelection(i);
                            if (reservaTec3.getSelectedItem().toString().equals(formularioAtual.getReservaTec3())) {
                                break;
                            }
                        }
                    }
                if (formularioAtual.getBackbone3().equals("-")) {
                    backbone3.setSelection(0);
                }else {
                    for (int i = 0; i < backbone3.getAdapter().getCount(); i++) {
                        backbone3.setSelection(i);
                        if (backbone3.getSelectedItem().toString().equals(formularioAtual.getBackbone3())) {
                            break;
                        }
                    }
                }
                if(formularioAtual.getPlacaIdent3().equals("Sim")){
                    placaIdentificadora3.setChecked(true);
                }
                if(formularioAtual.getDescidaCabos3().equals("Sim")){
                    descidaCabos3.setChecked(true);
                }
                descricaoIrregularidade3.setText(formularioAtual.getDescricaoIrregularidade3());
                observacaoMutuo3.setText((formularioAtual.getObservacaoMutuo3()));
                //4
                quantidadeCabos4.setText(formularioAtual.getQuantidadeCabos4());
                if (formularioAtual.getTipoCabo4().equals("-")) {
                    tipoCabo4.setSelection(0);
                }else {
                    for (int i = 0; i < tipoCabo4.getAdapter().getCount(); i++) {
                        tipoCabo4.setSelection(i);
                        if (tipoCabo4.getSelectedItem().toString().equals(formularioAtual.getTipoCabo4())) {
                            break;
                        }
                    }
                }
                nome4.setText(formularioAtual.getNome4());
                if (formularioAtual.getFinalidade4().equals("-")) {
                    finalidade4.setSelection(0);
                }else {
                    for (int i = 0; i < finalidade4.getAdapter().getCount(); i++) {
                        finalidade4.setSelection(i);
                        if (finalidade4.getSelectedItem().toString().equals(formularioAtual.getFinalidade4())) {
                            break;
                        }
                    }
                }
                    if (formularioAtual.getCeans4().equals("-")) {
                        ceans4.setSelection(0);
                    }else {
                        for (int i = 0; i < ceans4.getAdapter().getCount(); i++) {
                            ceans4.setSelection(i);
                            if (ceans4.getSelectedItem().toString().equals(formularioAtual.getCeans4())) {
                                break;
                            }
                        }
                    }
                if (formularioAtual.getTar4().equals("-")) {
                    tar4.setSelection(0);
                }else {
                    for (int i = 0; i < tar4.getAdapter().getCount(); i++) {
                        tar4.setSelection(i);
                        if (tar4.getSelectedItem().toString().equals(formularioAtual.getTar4())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getReservaTec4().equals("-")) {
                    reservaTec4.setSelection(0);
                }else {
                    for (int i = 0; i < reservaTec4.getAdapter().getCount(); i++) {
                        reservaTec4.setSelection(i);
                        if (reservaTec4.getSelectedItem().toString().equals(formularioAtual.getReservaTec4())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getBackbone4().equals("-")) {
                    backbone4.setSelection(0);
                }else {
                    for (int i = 0; i < backbone4.getAdapter().getCount(); i++) {
                        backbone4.setSelection(i);
                        if (backbone4.getSelectedItem().toString().equals(formularioAtual.getBackbone4())) {
                            break;
                        }
                    }
                }
                if(formularioAtual.getPlacaIdent4().equals("Sim")){
                    placaIdentificadora4.setChecked(true);
                }
                if(formularioAtual.getDescidaCabos4().equals("Sim")){
                    descidaCabos4.setChecked(true);
                }
                descricaoIrregularidade4.setText(formularioAtual.getDescricaoIrregularidade4());
                observacaoMutuo4.setText((formularioAtual.getObservacaoMutuo4()));
                //5
                quantidadeCabos5.setText(formularioAtual.getQuantidadeCabos5());
                if (formularioAtual.getTipoCabo5().equals("-")) {
                    tipoCabo5.setSelection(0);
                }else {
                    for (int i = 0; i < tipoCabo5.getAdapter().getCount(); i++) {
                        tipoCabo5.setSelection(i);
                        if (tipoCabo5.getSelectedItem().toString().equals(formularioAtual.getTipoCabo5())) {
                            break;
                        }
                    }
                }
                nome5.setText(formularioAtual.getNome5());
                if (formularioAtual.getFinalidade5().equals("-")) {
                    finalidade5.setSelection(0);
                }else {
                    for (int i = 0; i < finalidade5.getAdapter().getCount(); i++) {
                        finalidade5.setSelection(i);
                        if (finalidade5.getSelectedItem().toString().equals(formularioAtual.getFinalidade5())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getCeans5().equals("-")) {
                    ceans5.setSelection(0);
                }else {
                    for (int i = 0; i < ceans5.getAdapter().getCount(); i++) {
                        ceans5.setSelection(i);
                        if (ceans5.getSelectedItem().toString().equals(formularioAtual.getCeans5())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getTar5().equals("-")) {
                    tar5.setSelection(0);
                }else {
                    for (int i = 0; i < tar5.getAdapter().getCount(); i++) {
                        tar5.setSelection(i);
                        if (tar5.getSelectedItem().toString().equals(formularioAtual.getTar5())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getReservaTec5().equals("-")) {
                    reservaTec5.setSelection(0);
                }else {
                    for (int i = 0; i < reservaTec5.getAdapter().getCount(); i++) {
                        reservaTec5.setSelection(i);
                        if (reservaTec5.getSelectedItem().toString().equals(formularioAtual.getReservaTec5())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getBackbone5().equals("-")) {
                    backbone5.setSelection(0);
                }else {
                    for (int i = 0; i < backbone5.getAdapter().getCount(); i++) {
                        backbone5.setSelection(i);
                        if (backbone5.getSelectedItem().toString().equals(formularioAtual.getBackbone5())) {
                            break;
                        }
                    }
                }
                if(formularioAtual.getPlacaIdent5().equals("Sim")){
                    placaIdentificadora5.setChecked(true);
                }
                if(formularioAtual.getDescidaCabos5().equals("Sim")){
                    descidaCabos5.setChecked(true);
                }
                descricaoIrregularidade5.setText(formularioAtual.getDescricaoIrregularidade5());
                observacaoMutuo5.setText((formularioAtual.getObservacaoMutuo5()));

                if (formularioAtual.getDimensaoVegetacao().equals("-")) {
                    dimensaoVegetacao.setSelection(0);
                }else {
                    for (int i = 0; i < dimensaoVegetacao.getAdapter().getCount(); i++) {
                        dimensaoVegetacao.setSelection(i);
                        if (dimensaoVegetacao.getSelectedItem().toString().equals(formularioAtual.getDimensaoVegetacao())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getDistaciaBaixa().equals("-")) {
                    distaciaBaixa.setSelection(0);
                }else {
                    for (int i = 0; i < distaciaBaixa.getAdapter().getCount(); i++) {
                        distaciaBaixa.setSelection(i);
                        if (distaciaBaixa.getSelectedItem().toString().equals(formularioAtual.getDistaciaBaixa())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getDistanciaMedia().equals("-")) {
                    distanciaMedia.setSelection(0);
                }else {
                    for (int i = 0; i < distanciaMedia.getAdapter().getCount(); i++) {
                        distanciaMedia.setSelection(i);
                        if (dimensaoVegetacao.getSelectedItem().toString().equals(formularioAtual.getDistanciaMedia())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getEstadoArvore().equals("-")) {
                    estadoArvore.setSelection(0);
                }else {
                    for (int i = 0; i < estadoArvore.getAdapter().getCount(); i++) {
                        estadoArvore.setSelection(i);
                        if (estadoArvore.getSelectedItem().toString().equals(formularioAtual.getEstadoArvore())) {
                            break;
                        }
                    }
                }
                if(formularioAtual.getQuedaArvore().equals("Sim")){
                    quedaArvore.setChecked(true);
                }
                if (formularioAtual.getLocalArvore().equals("-")) {
                    localArvore.setSelection(0);
                }else {
                    for (int i = 0; i < localArvore.getAdapter().getCount(); i++) {
                        localArvore.setSelection(i);
                        if (localArvore.getSelectedItem().toString().equals(formularioAtual.getLocalArvore())) {
                            break;
                        }
                    }
                }
                observacaoVegetacao.setText(formularioAtual.getObservacaoVegetacao());
                Button btnNovoPoste = root.findViewById(R.id.btnNovoPoste);
                btnNovoPoste.setVisibility(View.VISIBLE);
                //add button to the layout
                btnNovoPoste.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //region pegar dados
                        progressDialog = new ProgressDialog(requireContext(), R.style.LightDialogTheme);
                        progressDialog.setMessage("Salvando..."); // Setting Message
                        progressDialog.setTitle("Por favor Espere"); // Setting Title
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                        progressDialog.show(); // Display Progress Dialog
                        progressDialog.setCancelable(false);
                        if ((imgPath == null) && (imgPath2 == null) && (imgPath3 == null)) {
                            Toast.makeText(requireContext(), "Preencha pelo menos uma foto", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else if (codigo.getText().toString().equals("") || codigo == null) {
                            Toast.makeText(requireContext(), "Preencha o campo de código", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else {
                            FormularioDAO formularioDAO = new FormularioDAO(requireActivity().getApplicationContext());
                            Formulario formulario = new Formulario();
                            formulario.setCodigo(Objects.requireNonNull(codigo.getText()).toString());
                            Log.i("TAG", imgPath);
                            if (imgPath != null) {
                                formulario.setCaminhoImagem(imgPath);
                            } else {
                                formulario.setCaminhoImagem("");
                            }
                            Log.i("TAG2", imgPath2);
                            if (imgPath2 != null) {
                                formulario.setCaminhoImagem2(imgPath2);
                            } else {
                                formulario.setCaminhoImagem2("");
                            }
                            Log.i("TAG3", imgPath3);
                            if (imgPath3 != null) {
                                formulario.setCaminhoImagem3(imgPath3);
                            } else {
                                formulario.setCaminhoImagem3("");
                            }
                            try {
                                Log.i("URL", urlFoto.toString());
                            } catch (Exception e) {

                            }
                            if (!novoUpload) {
                                urlFoto = null;
                            }
                            if (!novoUpload2) {
                                urlFoto2 = null;
                            }
                            if (!novoUpload3) {
                                urlFoto3 = null;
                            }
                            if ((urlFoto == null) || ((urlFoto.toString()).equals(""))) {
                                formulario.setUrlImagem("");
                                formulario.setColor(String.valueOf(R.color.design_default_color_error));
                            } else {
                                formulario.setUrlImagem(urlFoto.toString());
                                formulario.setColor(String.valueOf(R.color.colorPrimary));
                            }
                            if ((urlFoto2 == null) || ((urlFoto2.toString()).equals(""))) {
                                formulario.setUrlImagem2("");
                                formulario.setColor2(String.valueOf(R.color.design_default_color_error));
                            } else {
                                formulario.setUrlImagem2(urlFoto2.toString());
                                formulario.setColor2(String.valueOf(R.color.colorPrimary));
                            }
                            if ((urlFoto3 == null) || ((urlFoto3.toString()).equals(""))) {
                                formulario.setUrlImagem3("");
                                formulario.setColor3(String.valueOf(R.color.design_default_color_error));
                            } else {
                                formulario.setUrlImagem3(urlFoto3.toString());
                                formulario.setColor3(String.valueOf(R.color.colorPrimary));
                            }
                            //LOCALIZAÇÂO

                            formulario.setEndereco(Objects.requireNonNull(endereco.getText()).toString());
                            setLista(formulario, municipio, "municipio");
                            formulario.setLatitude(Objects.requireNonNull(latitude.getText()).toString());
                            formulario.setLongitude(Objects.requireNonNull(longitude.getText()).toString());
                            setLista(formulario, alturaCarga, "alturaCarga");


                            //CARACTERISTICAS FÍSICAS

                            setLista(formulario, tipoPoste, "tipoPoste");
                            if (normal.isChecked()) {
                                formulario.setNormal("Sim");
                            } else {
                                formulario.setNormal("Não");
                            }
                            if (ferragemExposta.isChecked()) {
                                formulario.setFerragemExposta("Sim");
                            } else {
                                formulario.setFerragemExposta("Não");
                            }
                            if (fletido.isChecked()) {
                                formulario.setFletido("Sim");
                            } else {
                                formulario.setFletido("Não");
                            }
                            if (danificado.isChecked()) {
                                formulario.setDanificado("Sim");
                            } else {
                                formulario.setDanificado("Não");
                            }
                            if (abalroado.isChecked()) {
                                formulario.setAbalroado("Sim");
                            } else {
                                formulario.setAbalroado("Não");
                            }
                            if (trincado.isChecked()) {
                                formulario.setTrincado("Sim");
                            } else {
                                formulario.setTrincado("Não");
                            }
                            formulario.setObservacaoFisicas(Objects.requireNonNull(observacaoFisicas.getText()).toString());


                            //ILUMINAÇÃO

                            if (ip.isChecked()) {
                                formulario.setIp("Sim");
                            } else {
                                formulario.setIp("Não");
                            }
                            setLista(formulario, ipEstrutura, "ipEstrutura");
                            formulario.setQuantidadeLampada(Objects.requireNonNull(quantidadeLampada.getText().toString()));
                            setLista(formulario, tipoPot, "tipoPot");
                            formulario.setPotReator(Objects.requireNonNull(potReator.getText()).toString());
                            setLista(formulario, ipAtivacao, "ipAtivacao");
                            if (vinteEQuatro.isChecked()) {
                                formulario.setVinteEQuatro("Sim");
                            } else {
                                formulario.setVinteEQuatro("Não");
                            }
                            formulario.setQuantidade24H(Objects.requireNonNull(quantidade24H.getText().toString()));

                            if (ip2.isChecked()) {
                                formulario.setIp2("Sim");
                            } else {
                                formulario.setIp2("Não");
                            }
                            setLista(formulario, ipEstrutura2, "ipEstrutura2");
                            formulario.setQuantidadeLampada2(Objects.requireNonNull(quantidadeLampada2.getText().toString()));
                            setLista(formulario, tipoPot2, "tipoPot2");
                            formulario.setPotReator2(Objects.requireNonNull(potReator2.getText()).toString());
                            setLista(formulario, ipAtivacao2, "ipAtivacao2");
                            if (vinteEQuatro2.isChecked()) {
                                formulario.setVinteEQuatro2("Sim");
                            } else {
                                formulario.setVinteEQuatro2("Não");
                            }
                            formulario.setQuantidade24H2(Objects.requireNonNull(quantidade24H2.getText().toString()));

                            if (ip3.isChecked()) {
                                formulario.setIp3("Sim");
                            } else {
                                formulario.setIp3("Não");
                            }
                            setLista(formulario, ipEstrutura3, "ipEstrutura3");
                            formulario.setQuantidadeLampada3(Objects.requireNonNull(quantidadeLampada3.getText().toString()));
                            setLista(formulario, tipoPot3, "tipoPot3");
                            formulario.setPotReator3(Objects.requireNonNull(potReator3.getText()).toString());
                            setLista(formulario, ipAtivacao3, "ipAtivacao3");
                            if (vinteEQuatro3.isChecked()) {
                                formulario.setVinteEQuatro3("Sim");
                            } else {
                                formulario.setVinteEQuatro3("Não");
                            }
                            formulario.setQuantidade24H3(Objects.requireNonNull(quantidade24H3.getText().toString()));
                            formulario.setObservacaoIP(Objects.requireNonNull(observacaoIP.getText().toString()));


                            //TRAFO

                            if (ativos.isChecked()) {
                                formulario.setAtivos("Sim");
                            } else {
                                formulario.setAtivos("Não");
                            }
                            if (chkTrafoTrifasico.isChecked()) {
                                formulario.setChkTrafoTrifasico("Sim");
                            } else {
                                formulario.setChkTrafoTrifasico("Não");
                            }
                            if (chkTrafoMono.isChecked()) {
                                formulario.setChkTrafoMono("Sim");
                            } else {
                                formulario.setChkTrafoMono("Não");
                            }
                            setLista(formulario, trafoTrifasico, "trafoTrifasico");
                            setLista(formulario, trafoMono, "trafoMono");
                            if (religador.isChecked()) {
                                formulario.setReligador("Sim");
                            } else {
                                formulario.setReligador("Não");
                            }
                            if (medicao.isChecked()) {
                                formulario.setMedicao("Sim");
                            } else {
                                formulario.setMedicao("Não");
                            }
                            if (chFusivel.isChecked()) {
                                formulario.setChFusivel("Sim");
                            } else {
                                formulario.setChFusivel("Não");
                            }
                            if (chFaca.isChecked()) {
                                formulario.setChFaca("Sim");
                            } else {
                                formulario.setChFaca("Não");
                            }
                            if (chBanco.isChecked()) {
                                formulario.setBanco("Sim");
                            } else {
                                formulario.setBanco("Não");
                            }
                            if (chFusivelReligador.isChecked()) {
                                formulario.setChFusivelReligador("Sim");
                            } else {
                                formulario.setChFusivelReligador("Não");
                            }
                            setLista(formulario, ramalSubt, "ramalSubt");
                            formulario.setObservacaoAtivos(Objects.requireNonNull(observacaoAtivos.getText()).toString());
                            formulario.setOutros(Objects.requireNonNull(outros.getText()).toString());

                            //MUTUO
                            if (mutuo.isChecked()) {
                                formulario.setMutuo("Sim");
                            } else {
                                formulario.setMutuo("Não");
                            }
                            setLista(formulario, quantidadeOcupantes, "quantidadeOcupantes");

                            formulario.setQuantidadeCabos(Objects.requireNonNull(quantidadeCabos.getText()).toString());
                            setLista(formulario, tipoCabo, "tipoCabo");
                            formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                            setLista(formulario, finalidade, "finalidade");
                            setLista(formulario, ceans, "ceans");
                            setLista(formulario, tar, "tar");
                            setLista(formulario, reservaTec, "reservaTec");
                            setLista(formulario, backbone, "backbone");
                            if (placaIdentificadora.isChecked()) {
                                formulario.setPlacaIdent("Sim");
                            } else {
                                formulario.setPlacaIdent("Não");
                            }

                            if (descidaCabos.isChecked()) {
                                formulario.setDescidaCabos("Sim");
                            } else {
                                formulario.setDescidaCabos("Não");
                            }
                            formulario.setDescricaoIrregularidade(Objects.requireNonNull(descricaoIrregularidade.getText()).toString());
                            formulario.setObservacaoMutuo(Objects.requireNonNull(observacaoMutuo.getText()).toString());

                            formulario.setQuantidadeCabos2(Objects.requireNonNull(quantidadeCabos2.getText()).toString());
                            formulario.setQuantidadeCabos2dois(Objects.requireNonNull(quantidadeCabos2dois.getText()).toString());
                            setLista(formulario, tipoCabo2, "tipoCabo2");
                            setLista(formulario, tipoCabo2dois, "tipoCabo2dois");
                            formulario.setNome2(Objects.requireNonNull(nome2.getText()).toString());
                            setLista(formulario, finalidade2, "finalidade2");
                            setLista(formulario, ceans2, "ceans2");
                            setLista(formulario, tar2, "tar2");
                            setLista(formulario, reservaTec2, "reservaTec2");
                            setLista(formulario, backbone2, "backbone2");
                            if (placaIdentificadora2.isChecked()) {
                                formulario.setPlacaIdent2("Sim");
                            } else {
                                formulario.setPlacaIdent2("Não");
                            }

                            if (descidaCabos2.isChecked()) {
                                formulario.setDescidaCabos2("Sim");
                            } else {
                                formulario.setDescidaCabos2("Não");
                            }
                            formulario.setDescricaoIrregularidade2(Objects.requireNonNull(descricaoIrregularidade2.getText()).toString());
                            formulario.setObservacaoMutuo2(Objects.requireNonNull(observacaoMutuo2.getText()).toString());

                            formulario.setQuantidadeCabos3(Objects.requireNonNull(quantidadeCabos3.getText()).toString());
                            setLista(formulario, tipoCabo3, "tipoCabo3");
                            formulario.setNome3(Objects.requireNonNull(nome3.getText()).toString());
                            setLista(formulario, finalidade3, "finalidade3");
                            setLista(formulario, ceans3, "ceans3");
                            setLista(formulario, tar3, "tar3");
                            setLista(formulario, reservaTec3, "reservaTec3");
                            setLista(formulario, backbone3, "backbone3");
                            if (placaIdentificadora3.isChecked()) {
                                formulario.setPlacaIdent3("Sim");
                            } else {
                                formulario.setPlacaIdent3("Não");
                            }

                            if (descidaCabos3.isChecked()) {
                                formulario.setDescidaCabos3("Sim");
                            } else {
                                formulario.setDescidaCabos3("Não");
                            }
                            formulario.setDescricaoIrregularidade3(Objects.requireNonNull(descricaoIrregularidade3.getText()).toString());
                            formulario.setObservacaoMutuo3(Objects.requireNonNull(observacaoMutuo3.getText()).toString());

                            formulario.setQuantidadeCabos4(Objects.requireNonNull(quantidadeCabos4.getText()).toString());
                            setLista(formulario, tipoCabo4, "tipoCabo4");
                            formulario.setNome4(Objects.requireNonNull(nome4.getText()).toString());
                            setLista(formulario, finalidade4, "finalidade4");
                            setLista(formulario, ceans4, "ceans4");
                            setLista(formulario, tar4, "tar4");
                            setLista(formulario, reservaTec4, "reservaTec4");
                            setLista(formulario, backbone4, "backbone4");
                            if (placaIdentificadora4.isChecked()) {
                                formulario.setPlacaIdent4("Sim");
                            } else {
                                formulario.setPlacaIdent4("Não");
                            }

                            if (descidaCabos4.isChecked()) {
                                formulario.setDescidaCabos4("Sim");
                            } else {
                                formulario.setDescidaCabos4("Não");
                            }
                            formulario.setDescricaoIrregularidade4(Objects.requireNonNull(descricaoIrregularidade4.getText()).toString());
                            formulario.setObservacaoMutuo4(Objects.requireNonNull(observacaoMutuo4.getText()).toString());

                            formulario.setQuantidadeCabos5(Objects.requireNonNull(quantidadeCabos5.getText()).toString());
                            setLista(formulario, tipoCabo5, "tipoCabo5");
                            formulario.setNome5(Objects.requireNonNull(nome5.getText()).toString());
                            setLista(formulario, finalidade5, "finalidade5");
                            setLista(formulario, ceans5, "ceans5");
                            setLista(formulario, tar5, "tar5");
                            setLista(formulario, reservaTec5, "reservaTec5");
                            setLista(formulario, backbone5, "backbone5");
                            if (placaIdentificadora5.isChecked()) {
                                formulario.setPlacaIdent5("Sim");
                            } else {
                                formulario.setPlacaIdent5("Não");
                            }

                            if (descidaCabos5.isChecked()) {
                                formulario.setDescidaCabos5("Sim");
                            } else {
                                formulario.setDescidaCabos5("Não");
                            }
                            formulario.setDescricaoIrregularidade5(Objects.requireNonNull(descricaoIrregularidade5.getText()).toString());
                            formulario.setObservacaoMutuo5(Objects.requireNonNull(observacaoMutuo5.getText()).toString());


                            //VEGETAÇÃO
                            setLista(formulario, dimensaoVegetacao, "dimensaoVegetacao");
                            setLista(formulario, distaciaBaixa, "distanciaBaixa");
                            setLista(formulario, distanciaMedia, "distanciaMedia");
                            setLista(formulario, estadoArvore, "estadoArvore");
                            if (quedaArvore.isChecked()) {
                                formulario.setQuedaArvore("Sim");
                            } else {
                                formulario.setQuedaArvore("Não");
                            }
                            setLista(formulario, localArvore, "localArvore");
                            formulario.setObservacaoVegetacao(Objects.requireNonNull(observacaoVegetacao.getText()).toString());

                            String thisDayText, thisMonthText, thisYearText;
                            //region Inicialização da data
                            Calendar calendar = Calendar.getInstance();

                            int thisYear = calendar.get(Calendar.YEAR);
                            thisYearText = String.valueOf(thisYear);

                            int thisMonth = calendar.get(Calendar.MONTH) + 1;
                            if (thisMonth < 9) {
                                thisMonthText = "0" + thisMonth;
                            } else {
                                thisMonthText = String.valueOf(thisMonth);
                            }

                            int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
                            if (thisDay < 9) {
                                thisDayText = "0" + thisDay;
                            } else {
                                thisDayText = String.valueOf(thisDay);
                            }
                            String timeStamp = new SimpleDateFormat("dd/MM/yy-HH:mm:ss").format(new Date());
                            SimpleDateFormat readDate = new SimpleDateFormat("dd/MM/yy-HH:mm:ss");
                            readDate.setTimeZone(TimeZone.getTimeZone("GMT"));
                            Date date = null;
                            try {
                                date = readDate.parse(timeStamp);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            SimpleDateFormat writeDate = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
                            writeDate.setTimeZone(TimeZone.getTimeZone("GMT-04:00"));
                            String s = writeDate.format(date);


                            Log.i("DATA", s);

                            String data = thisDayText + "/" + thisMonthText + "/" + thisYearText;
                            formulario.setData(s);
                            if (formularioDAO.salvar(formulario)) {
                                CadastradosFragment cadastradosFragment = new CadastradosFragment();

                                FragmentManager fm = getParentFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                                transaction.commit();
                                Toast.makeText(requireActivity().getApplicationContext(), "Sucesso ao salvar formulário", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(requireActivity().getApplicationContext(), "Erro ao salvar formulário", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            //endregion
                            //endregion
                        }

                    }
                });

                controle = false;

            }
        }catch (Exception e ){
         Log.i("ERRO", "erro: " + e);
        }


        normal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(normal.isChecked()){
                    ferragemExposta.setChecked(false);
                    fletido.setChecked(false);
                    danificado.setChecked(false);
                    abalroado.setChecked(false);
                    trincado.setChecked(false);
                    ferragemExposta.setEnabled(false);
                    fletido.setEnabled(false);
                    danificado.setEnabled(false);
                    abalroado.setEnabled(false);
                    trincado.setEnabled(false);
                }else{
                    ferragemExposta.setEnabled(true);
                    fletido.setEnabled(true);
                    danificado.setEnabled(true);
                    abalroado.setEnabled(true);
                    trincado.setEnabled(true);
                }


            }
        }
        );

        vinteEQuatro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(vinteEQuatro.isChecked()){
                    quantidade24H.setEnabled(true);
                }else{
                    quantidade24H.setEnabled(false);
                    quantidade24H.setText("");
                }
            }
        }
        );

        vinteEQuatro2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if(vinteEQuatro2.isChecked()){
             quantidade24H2.setEnabled(true);
          }else{
             quantidade24H2.setEnabled(false);
              quantidade24H2.setText("");
          }
              }
           }
        );

        vinteEQuatro3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(vinteEQuatro3.isChecked()){
              quantidade24H3.setEnabled(true);
            }else{
              quantidade24H3.setEnabled(false);
              quantidade24H3.setText("");
            }
          }
        }
        );


        //Listener para só habilitar os dados da própria ip e as próximas ip caso a primeira
        // tenha sido checada
        ip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ip.isChecked()){
                    ipEstrutura.setEnabled(true);
                    tipoPot.setEnabled(true);
                    potReator.setEnabled(true);
                    quantidadeLampada.setEnabled(true);
                    ipAtivacao.setEnabled(true);
                    vinteEQuatro.setEnabled(true);
                    ip2.setEnabled(true);
                    ip2.setVisibility(View.VISIBLE);
                    foto4.setEnabled(true);
                    foto4.setVisibility(View.VISIBLE);
                    btnFoto4.setVisibility(View.VISIBLE);
                    btnUpload4.setVisibility(View.VISIBLE);
                    //foto5.setEnabled(true);
                    //foto5.setVisibility(View.VISIBLE);
                    //btnFoto5.setVisibility(View.VISIBLE);
                    //btnUpload5.setVisibility(View.VISIBLE);
                    //foto6.setEnabled(true);
                    //foto6.setVisibility(View.VISIBLE);
                    //btnFoto6.setVisibility(View.VISIBLE);
                    //btnUpload6.setVisibility(View.VISIBLE);

                    ipEstrutura.setVisibility(View.VISIBLE);
                    tipoPot.setVisibility(View.VISIBLE);
                    potReator.setVisibility(View.VISIBLE);
                    quantidadeLampada.setVisibility(View.VISIBLE);
                    ipAtivacao.setVisibility(View.VISIBLE);
                    vinteEQuatro.setVisibility(View.VISIBLE);
                    quantidade24H.setVisibility(View.VISIBLE);
                }else{
                    ipEstrutura.setEnabled(false);
                    quantidadeLampada.setEnabled(false);
                    tipoPot.setEnabled(false);
                    tipoPot.setSelection(0);
                    potReator.setEnabled(false);
                    potReator.setText("");
                    quantidadeLampada.setText("");
                    ipEstrutura.setSelection(0);
                    ipAtivacao.setEnabled(false);
                    vinteEQuatro.setEnabled(false);
                    vinteEQuatro.setChecked(false);
                    ipAtivacao.setSelection(0);
                    ip2.setChecked(false);
                    ip2.setEnabled(false);
                    ip2.setVisibility(View.GONE);
                    foto4.setVisibility(View.GONE);
                    btnFoto4.setVisibility(View.GONE);
                    btnUpload4.setVisibility(View.GONE);
                    foto5.setVisibility(View.GONE);
                    btnFoto5.setVisibility(View.GONE);
                    btnUpload5.setVisibility(View.GONE);
                    foto6.setVisibility(View.GONE);
                    btnFoto6.setVisibility(View.GONE);
                    btnUpload6.setVisibility(View.GONE);

                    ipEstrutura.setVisibility(View.GONE);
                    tipoPot.setVisibility(View.GONE);
                    potReator.setVisibility(View.GONE);
                    quantidadeLampada.setVisibility(View.GONE);
                    quantidade24H.setVisibility(View.GONE);
                    ipAtivacao.setVisibility(View.GONE);
                    vinteEQuatro.setVisibility(View.GONE);
                }
            }
        });

        //Listener para só habilitar os dados da própria ip e as próximas ip caso a segunda
        // tenha sido checada
        ip2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ip2.isChecked()){
                    ipEstrutura2.setEnabled(true);
                    tipoPot2.setEnabled(true);
                    potReator2.setEnabled(true);
                    quantidadeLampada2.setEnabled(true);
                    ipAtivacao2.setEnabled(true);
                    vinteEQuatro2.setEnabled(true);
                    ip3.setEnabled(true);
                    foto7.setEnabled(true);
                    foto7.setVisibility(View.VISIBLE);
                    btnFoto7.setVisibility(View.VISIBLE);
                    btnUpload7.setVisibility(View.VISIBLE);
                    //foto8.setEnabled(true);
                    //foto8.setVisibility(View.VISIBLE);
                    //btnFoto8.setVisibility(View.VISIBLE);
                    //btnUpload8.setVisibility(View.VISIBLE);
                    //foto9.setEnabled(true);
                    //foto9.setVisibility(View.VISIBLE);
                    //btnFoto9.setVisibility(View.VISIBLE);
                    //btnUpload9.setVisibility(View.VISIBLE);

                    ipEstrutura2.setVisibility(View.VISIBLE);
                    tipoPot2.setVisibility(View.VISIBLE);
                    potReator2.setVisibility(View.VISIBLE);
                    quantidadeLampada2.setVisibility(View.VISIBLE);
                    ipAtivacao2.setVisibility(View.VISIBLE);
                    vinteEQuatro2.setVisibility(View.VISIBLE);
                    quantidade24H2.setVisibility(View.VISIBLE);
                    ip3.setVisibility(View.VISIBLE);
                }else{
                    ipEstrutura2.setEnabled(false);
                    quantidadeLampada2.setEnabled(false);
                    tipoPot2.setEnabled(false);
                    tipoPot2.setSelection(0);
                    potReator2.setEnabled(false);
                    potReator2.setText("");
                    quantidadeLampada2.setText("");
                    ipEstrutura2.setSelection(0);
                    ipAtivacao2.setEnabled(false);
                    vinteEQuatro2.setChecked(false);
                    vinteEQuatro2.setEnabled(false);
                    ipAtivacao2.setSelection(0);
                    ip3.setChecked(false);
                    ip3.setEnabled(false);

                    foto7.setVisibility(View.GONE);
                    btnFoto7.setVisibility(View.GONE);
                    btnUpload7.setVisibility(View.GONE);
                    foto8.setVisibility(View.GONE);
                    btnFoto8.setVisibility(View.GONE);
                    btnUpload8.setVisibility(View.GONE);
                    foto9.setVisibility(View.GONE);
                    btnFoto9.setVisibility(View.GONE);
                    btnUpload9.setVisibility(View.GONE);

                    ipEstrutura2.setVisibility(View.GONE);
                    tipoPot2.setVisibility(View.GONE);
                    potReator2.setVisibility(View.GONE);
                    quantidadeLampada2.setVisibility(View.GONE);
                    quantidade24H2.setVisibility(View.GONE);
                    ipAtivacao2.setVisibility(View.GONE);
                    vinteEQuatro2.setVisibility(View.GONE);
                    ip3.setVisibility(View.GONE);
                }
            }
        });

        //Listener para só habilitar os dados da própria ip caso tenha sido checada
        ip3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ip3.isChecked()){
                    ipEstrutura3.setEnabled(true);
                    tipoPot3.setEnabled(true);
                    potReator3.setEnabled(true);
                    quantidadeLampada3.setEnabled(true);
                    ipAtivacao3.setEnabled(true);
                    vinteEQuatro3.setEnabled(true);

                    foto10.setEnabled(true);
                    foto10.setVisibility(View.VISIBLE);
                    btnFoto10.setVisibility(View.VISIBLE);
                    btnUpload10.setVisibility(View.VISIBLE);
                    /*foto11.setEnabled(true);
                    foto11.setVisibility(View.VISIBLE);
                    btnFoto11.setVisibility(View.VISIBLE);
                    btnUpload11.setVisibility(View.VISIBLE);
                    foto12.setEnabled(true);
                    foto12.setVisibility(View.VISIBLE);
                    btnFoto12.setVisibility(View.VISIBLE);
                    btnUpload12.setVisibility(View.VISIBLE);*/

                    ipEstrutura3.setVisibility(View.VISIBLE);
                    tipoPot3.setVisibility(View.VISIBLE);
                    potReator3.setVisibility(View.VISIBLE);
                    quantidadeLampada3.setVisibility(View.VISIBLE);
                    ipAtivacao3.setVisibility(View.VISIBLE);
                    vinteEQuatro3.setVisibility(View.VISIBLE);
                    quantidade24H3.setVisibility(View.VISIBLE);

                }else{
                    ipEstrutura3.setEnabled(false);
                    quantidadeLampada3.setEnabled(false);
                    tipoPot3.setEnabled(false);
                    tipoPot3.setSelection(0);
                    potReator3.setEnabled(false);
                    potReator3.setText("");
                    quantidadeLampada3.setText("");
                    ipEstrutura3.setSelection(0);
                    ipAtivacao3.setEnabled(false);
                    vinteEQuatro3.setChecked(false);
                    vinteEQuatro3.setEnabled(false);
                    ipAtivacao3.setSelection(0);

                    foto10.setVisibility(View.GONE);
                    btnFoto10.setVisibility(View.GONE);
                    btnUpload10.setVisibility(View.GONE);
                    foto11.setVisibility(View.GONE);
                    btnFoto11.setVisibility(View.GONE);
                    btnUpload11.setVisibility(View.GONE);
                    foto12.setVisibility(View.GONE);
                    btnFoto12.setVisibility(View.GONE);
                    btnUpload12.setVisibility(View.GONE);

                    ipEstrutura3.setVisibility(View.GONE);
                    tipoPot3.setVisibility(View.GONE);
                    potReator3.setVisibility(View.GONE);
                    quantidadeLampada3.setVisibility(View.GONE);
                    quantidade24H3.setVisibility(View.GONE);
                    ipAtivacao3.setVisibility(View.GONE);
                    vinteEQuatro3.setVisibility(View.GONE);
                }
            }
        });

        ativos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ativos.isChecked()){
                    chkTrafoTrifasico.setEnabled(true);
                    chkTrafoMono.setEnabled(true);
                    religador.setEnabled(true);
                    medicao.setEnabled(true);
                    chFusivel.setEnabled(true);
                    chFaca.setEnabled(true);
                    chFusivelReligador.setEnabled(true);
                    chBanco.setEnabled(true);
                    ramalSubt.setEnabled(true);
                    outros.setEnabled(true);

                    foto13.setEnabled(true);
                    foto13.setVisibility(View.VISIBLE);
                    btnFoto13.setVisibility(View.VISIBLE);
                    btnUpload13.setVisibility(View.VISIBLE);
                    foto14.setEnabled(true);
                    foto14.setVisibility(View.VISIBLE);
                    btnFoto14.setVisibility(View.VISIBLE);
                    btnUpload14.setVisibility(View.VISIBLE);

                    trafoMono.setVisibility(View.VISIBLE);
                    trafoTrifasico.setVisibility(View.VISIBLE);
                    chkTrafoTrifasico.setVisibility(View.VISIBLE);
                    chkTrafoMono.setVisibility(View.VISIBLE);
                    religador.setVisibility(View.VISIBLE);
                    medicao.setVisibility(View.VISIBLE);
                    chFusivel.setVisibility(View.VISIBLE);
                    chFaca.setVisibility(View.VISIBLE);
                    chFusivelReligador.setVisibility(View.VISIBLE);
                    chBanco.setVisibility(View.VISIBLE);
                    ramalSubt.setVisibility(View.VISIBLE);
                    outros.setVisibility(View.VISIBLE);
                }else{
                    chkTrafoTrifasico.setChecked(false);
                    chkTrafoTrifasico.setEnabled(false);
                    chkTrafoMono.setChecked(false);
                    chkTrafoMono.setEnabled(false);
                    chkTrafoTrifasico.setChecked(false);
                    trafoTrifasico.setSelection(0);
                    trafoTrifasico.setEnabled(false);
                    trafoMono.setSelection(0);
                    trafoMono.setEnabled(false);
                    religador.setEnabled(false);
                    religador.setChecked(false);
                    medicao.setEnabled(false);
                    medicao.setChecked(false);
                    chFusivel.setEnabled(false);
                    chFusivel.setChecked(false);
                    chFaca.setEnabled(false);
                    chFaca.setChecked(false);
                    chFusivelReligador.setEnabled(false);
                    chFusivelReligador.setChecked(false);
                    chBanco.setEnabled(false);
                    chBanco.setChecked(false);
                    ramalSubt.setSelection(0);
                    ramalSubt.setEnabled(false);
                    outros.setEnabled(false);

                    trafoMono.setVisibility(View.INVISIBLE);
                    trafoTrifasico.setVisibility(View.GONE);
                    chkTrafoTrifasico.setVisibility(View.GONE);
                    chkTrafoMono.setVisibility(View.GONE);
                    religador.setVisibility(View.GONE);
                    medicao.setVisibility(View.GONE);
                    chFusivel.setVisibility(View.GONE);
                    chFaca.setVisibility(View.GONE);
                    chFusivelReligador.setVisibility(View.GONE);
                    chBanco.setVisibility(View.GONE);
                    ramalSubt.setVisibility(View.GONE);
                    outros.setVisibility(View.GONE);
                    foto13.setVisibility(View.GONE);
                    btnFoto13.setVisibility(View.GONE);
                    btnUpload13.setVisibility(View.GONE);
                    foto14.setVisibility(View.GONE);
                    btnFoto14.setVisibility(View.GONE);
                    btnUpload14.setVisibility(View.GONE);
                }
            }
        });

        chkTrafoTrifasico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkTrafoTrifasico.isChecked()){
                    chkTrafoMono.setChecked(false);
                    trafoMono.setSelection(0);
                    trafoMono.setEnabled(false);
                    trafoTrifasico.setEnabled(true);
                }else{
                    trafoTrifasico.setSelection(0);
                    trafoTrifasico.setEnabled(false);
                }

            }
        });
        chkTrafoMono.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkTrafoMono.isChecked()){
                    chkTrafoTrifasico.setChecked(false);
                    trafoTrifasico.setSelection(0);
                    trafoTrifasico.setEnabled(false);
                    trafoMono.setEnabled(true);
                }else{
                    trafoMono.setSelection(0);
                    trafoMono.setEnabled(false);
                }

            }
        });

        mutuo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("CHAMADO", "FUI");
                    if(mutuo.isChecked()){
                    quantidadeOcupantes.setEnabled(true);
                    quantidadeOcupantes.setVisibility(View.VISIBLE);
                }else{
                    quantidadeOcupantes.setSelection(0);
                    quantidadeOcupantes.setEnabled(false);
                    //quantidadeOcupantes.setVisibility(View.GONE);
                }
            }
        });

        quantidadeOcupantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("CHAMADO", "FUI2");
                if (controle){
                    Log.i("CHAMADO", "PREVENIU");
                }else{
                    if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(0).toString())) {
                        Log.i("Spinenr", "Apertei 0");
                        // region MutuoDados0
                        mutuoDados(quantidadeCabos, null, false);
                        mutuoDados(quantidadeCabos2, null, false);
                        mutuoDados(quantidadeCabos2dois, null, false);
                        mutuoDados(quantidadeCabos3, null, false);
                        mutuoDados(quantidadeCabos4, null, false);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, false);
                        mutuoDados(null, tipoCabo2, false);
                        mutuoDados(null, tipoCabo2dois, false);
                        mutuoDados(null, tipoCabo3, false);
                        mutuoDados(null, tipoCabo4, false);
                        mutuoDados(null, tipoCabo5, false);

                        nome.setVisibility(View.GONE);
                        nome2.setVisibility(View.GONE);
                        mutuoDados(nome3, null, false);
                        mutuoDados(nome4, null, false);
                        mutuoDados(nome5, null, false);

                        mutuoDados(null, finalidade, false);
                        mutuoDados(null, finalidade2, false);
                        mutuoDados(null, finalidade3, false);
                        mutuoDados(null, finalidade4, false);
                        mutuoDados(null, finalidade5, false);

                        mutuoDados(null, ceans, false);
                        mutuoDados(null, ceans2, false);
                        mutuoDados(null, ceans3, false);
                        mutuoDados(null, ceans4, false);
                        mutuoDados(null, ceans5, false);

                        mutuoDados(null, tar, false);
                        mutuoDados(null, tar2, false);
                        mutuoDados(null, tar3, false);
                        mutuoDados(null, tar4, false);
                        mutuoDados(null, tar5, false);

                        mutuoDados(null, reservaTec, false);
                        mutuoDados(null, reservaTec2, false);
                        mutuoDados(null, reservaTec3, false);
                        mutuoDados(null, reservaTec4, false);
                        mutuoDados(null, reservaTec5, false);

                        mutuoDados(null, backbone, false);
                        mutuoDados(null, backbone2, false);
                        mutuoDados(null, backbone3, false);
                        mutuoDados(null, backbone4, false);
                        mutuoDados(null, backbone5, false);

                        mutuoDados(descricaoIrregularidade, null, false);
                        mutuoDados(descricaoIrregularidade2, null, false);
                        mutuoDados(descricaoIrregularidade3, null, false);
                        mutuoDados(descricaoIrregularidade4, null, false);
                        mutuoDados(descricaoIrregularidade5, null, false);

                        mutuoDados(observacaoMutuo, null, false);
                        mutuoDados(observacaoMutuo2, null, false);
                        mutuoDados(observacaoMutuo3, null, false);
                        mutuoDados(observacaoMutuo4, null, false);
                        mutuoDados(observacaoMutuo5, null, false);

                        placaIdentificadora.setChecked(false);
                        placaIdentificadora.setEnabled(false);
                        placaIdentificadora2.setChecked(false);
                        placaIdentificadora2.setEnabled(false);
                        placaIdentificadora3.setChecked(false);
                        placaIdentificadora3.setEnabled(false);
                        placaIdentificadora4.setChecked(false);
                        placaIdentificadora4.setEnabled(false);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);

                        placaIdentificadora.setVisibility(View.GONE);
                        placaIdentificadora2.setVisibility(View.GONE);
                        placaIdentificadora3.setVisibility(View.GONE);
                        placaIdentificadora4.setVisibility(View.GONE);
                        placaIdentificadora5.setVisibility(View.GONE);

                        descidaCabos.setChecked(false);
                        descidaCabos.setEnabled(false);
                        descidaCabos2.setChecked(false);
                        descidaCabos2.setEnabled(false);
                        descidaCabos3.setChecked(false);
                        descidaCabos3.setEnabled(false);
                        descidaCabos4.setChecked(false);
                        descidaCabos4.setEnabled(false);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        descidaCabos.setVisibility(View.GONE);
                        descidaCabos2.setVisibility(View.GONE);
                        descidaCabos3.setVisibility(View.GONE);
                        descidaCabos4.setVisibility(View.GONE);
                        descidaCabos5.setVisibility(View.GONE);

                        mutuo2.setVisibility(View.GONE);
                        mutuo3.setVisibility(View.GONE);
                        mutuo4.setVisibility(View.GONE);
                        mutuo5.setVisibility(View.GONE);

                        foto15.setVisibility(View.GONE);
                        btnFoto15.setVisibility(View.GONE);
                        btnUpload15.setVisibility(View.GONE);
                        foto16.setVisibility(View.GONE);
                        btnFoto16.setVisibility(View.GONE);
                        btnUpload16.setVisibility(View.GONE);
                        foto17.setVisibility(View.GONE);
                        btnFoto17.setVisibility(View.GONE);
                        btnUpload17.setVisibility(View.GONE);


                        // endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(1).toString())) {
                        Log.i("Spinenr", "Apertei 1");
                        // region MutuoDados1
                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, false);
                        mutuoDados(quantidadeCabos2dois, null, false);
                        mutuoDados(quantidadeCabos3, null, false);
                        mutuoDados(quantidadeCabos4, null, false);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, false);
                        mutuoDados(null, tipoCabo2dois, false);
                        mutuoDados(null, tipoCabo3, false);
                        mutuoDados(null, tipoCabo4, false);
                        mutuoDados(null, tipoCabo5, false);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.GONE);
                        mutuoDados(nome3, null, false);
                        mutuoDados(nome4, null, false);
                        mutuoDados(nome5, null, false);

                        mutuoDados(null, finalidade, true);
                        mutuoDados(null, finalidade2, false);
                        mutuoDados(null, finalidade3, false);
                        mutuoDados(null, finalidade4, false);
                        mutuoDados(null, finalidade5, false);

                        mutuoDados(null, ceans, true);
                        mutuoDados(null, ceans2, false);
                        mutuoDados(null, ceans3, false);
                        mutuoDados(null, ceans4, false);
                        mutuoDados(null, ceans5, false);

                        mutuoDados(null, tar, true);
                        mutuoDados(null, tar2, false);
                        mutuoDados(null, tar3, false);
                        mutuoDados(null, tar4, false);
                        mutuoDados(null, tar5, false);

                        mutuoDados(null, reservaTec, true);
                        mutuoDados(null, reservaTec2, false);
                        mutuoDados(null, reservaTec3, false);
                        mutuoDados(null, reservaTec4, false);
                        mutuoDados(null, reservaTec5, false);

                        mutuoDados(null, backbone, true);
                        mutuoDados(null, backbone2, false);
                        mutuoDados(null, backbone3, false);
                        mutuoDados(null, backbone4, false);
                        mutuoDados(null, backbone5, false);

                        mutuoDados(descricaoIrregularidade, null, true);
                        mutuoDados(descricaoIrregularidade2, null, false);
                        mutuoDados(descricaoIrregularidade3, null, false);
                        mutuoDados(descricaoIrregularidade4, null, false);
                        mutuoDados(descricaoIrregularidade5, null, false);

                        mutuoDados(observacaoMutuo, null, true);
                        mutuoDados(observacaoMutuo2, null, false);
                        mutuoDados(observacaoMutuo3, null, false);
                        mutuoDados(observacaoMutuo4, null, false);
                        mutuoDados(observacaoMutuo5, null, false);


                        placaIdentificadora.setEnabled(true);
                        placaIdentificadora2.setChecked(false);
                        placaIdentificadora2.setEnabled(false);
                        placaIdentificadora3.setChecked(false);
                        placaIdentificadora3.setEnabled(false);
                        placaIdentificadora4.setChecked(false);
                        placaIdentificadora4.setEnabled(false);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);

                        placaIdentificadora.setVisibility(View.VISIBLE);
                        placaIdentificadora2.setVisibility(View.GONE);
                        placaIdentificadora3.setVisibility(View.GONE);
                        placaIdentificadora4.setVisibility(View.GONE);
                        placaIdentificadora5.setVisibility(View.GONE);
                        descidaCabos.setVisibility(View.VISIBLE);
                        descidaCabos2.setVisibility(View.GONE);
                        descidaCabos3.setVisibility(View.GONE);
                        descidaCabos4.setVisibility(View.GONE);
                        descidaCabos5.setVisibility(View.GONE);


                        descidaCabos.setEnabled(true);
                        descidaCabos2.setChecked(false);
                        descidaCabos2.setEnabled(false);
                        descidaCabos3.setChecked(false);
                        descidaCabos3.setEnabled(false);
                        descidaCabos4.setChecked(false);
                        descidaCabos4.setEnabled(false);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        mutuo2.setVisibility(View.GONE);
                        mutuo3.setVisibility(View.GONE);
                        mutuo4.setVisibility(View.GONE);
                        mutuo5.setVisibility(View.GONE);

                        foto15.setVisibility(View.VISIBLE);
                        btnFoto15.setVisibility(View.VISIBLE);
                        btnUpload15.setVisibility(View.VISIBLE);
                        foto16.setVisibility(View.VISIBLE);
                        btnFoto16.setVisibility(View.VISIBLE);
                        btnUpload16.setVisibility(View.VISIBLE);
                        foto17.setVisibility(View.VISIBLE);
                        btnFoto17.setVisibility(View.VISIBLE);
                        btnUpload17.setVisibility(View.VISIBLE);

                        //endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(2).toString())) {
                        Log.i("Spinenr", "Apertei 2");
                        //region MutuoDados2

                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, true);
                        mutuoDados(quantidadeCabos2dois, null, true);
                        mutuoDados(quantidadeCabos3, null, false);
                        mutuoDados(quantidadeCabos4, null, false);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, true);
                        mutuoDados(null, tipoCabo2dois, true);
                        mutuoDados(null, tipoCabo3, false);
                        mutuoDados(null, tipoCabo4, false);
                        mutuoDados(null, tipoCabo5, false);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.VISIBLE);
                        mutuoDados(nome3, null, false);
                        mutuoDados(nome4, null, false);
                        mutuoDados(nome5, null, false);

                        mutuoDados(null, finalidade, true);
                        mutuoDados(null, finalidade2, true);
                        mutuoDados(null, finalidade3, false);
                        mutuoDados(null, finalidade4, false);
                        mutuoDados(null, finalidade5, false);

                        mutuoDados(null, ceans, true);
                        mutuoDados(null, ceans2, true);
                        mutuoDados(null, ceans3, false);
                        mutuoDados(null, ceans4, false);
                        mutuoDados(null, ceans5, false);

                        mutuoDados(null, tar, true);
                        mutuoDados(null, tar2, true);
                        mutuoDados(null, tar3, false);
                        mutuoDados(null, tar4, false);
                        mutuoDados(null, tar5, false);

                        mutuoDados(null, reservaTec, true);
                        mutuoDados(null, reservaTec2, true);
                        mutuoDados(null, reservaTec3, false);
                        mutuoDados(null, reservaTec4, false);
                        mutuoDados(null, reservaTec5, false);

                        mutuoDados(null, backbone, true);
                        mutuoDados(null, backbone2, true);
                        mutuoDados(null, backbone3, false);
                        mutuoDados(null, backbone4, false);
                        mutuoDados(null, backbone5, false);

                        mutuoDados(descricaoIrregularidade, null, true);
                        mutuoDados(descricaoIrregularidade2, null, true);
                        mutuoDados(descricaoIrregularidade3, null, false);
                        mutuoDados(descricaoIrregularidade4, null, false);
                        mutuoDados(descricaoIrregularidade5, null, false);

                        mutuoDados(observacaoMutuo, null, true);
                        mutuoDados(observacaoMutuo2, null, true);
                        mutuoDados(observacaoMutuo3, null, false);
                        mutuoDados(observacaoMutuo4, null, false);
                        mutuoDados(observacaoMutuo5, null, false);


                        placaIdentificadora.setEnabled(true);

                        placaIdentificadora2.setEnabled(true);
                        placaIdentificadora3.setChecked(false);
                        placaIdentificadora3.setEnabled(false);
                        placaIdentificadora4.setChecked(false);
                        placaIdentificadora4.setEnabled(false);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);


                        descidaCabos.setEnabled(true);

                        descidaCabos2.setEnabled(true);
                        descidaCabos3.setChecked(false);
                        descidaCabos3.setEnabled(false);
                        descidaCabos4.setChecked(false);
                        descidaCabos4.setEnabled(false);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        placaIdentificadora.setVisibility(View.VISIBLE);
                        placaIdentificadora2.setVisibility(View.VISIBLE);
                        placaIdentificadora3.setVisibility(View.GONE);
                        placaIdentificadora4.setVisibility(View.GONE);
                        placaIdentificadora5.setVisibility(View.GONE);
                        descidaCabos.setVisibility(View.VISIBLE);
                        descidaCabos2.setVisibility(View.VISIBLE);
                        descidaCabos3.setVisibility(View.GONE);
                        descidaCabos4.setVisibility(View.GONE);
                        descidaCabos5.setVisibility(View.GONE);

                        mutuo2.setVisibility(View.VISIBLE);
                        mutuo3.setVisibility(View.GONE);
                        mutuo4.setVisibility(View.GONE);
                        mutuo5.setVisibility(View.GONE);

                        foto15.setVisibility(View.VISIBLE);
                        btnFoto15.setVisibility(View.VISIBLE);
                        btnUpload15.setVisibility(View.VISIBLE);
                        foto16.setVisibility(View.VISIBLE);
                        btnFoto16.setVisibility(View.VISIBLE);
                        btnUpload16.setVisibility(View.VISIBLE);
                        foto17.setVisibility(View.VISIBLE);
                        btnFoto17.setVisibility(View.VISIBLE);
                        btnUpload17.setVisibility(View.VISIBLE);
                        //endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(3).toString())) {
                        Log.i("Spinenr", "Apertei 3");
                        //region MutuoDados3

                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, true);
                        mutuoDados(quantidadeCabos2dois, null, true);
                        mutuoDados(quantidadeCabos3, null, true);
                        mutuoDados(quantidadeCabos4, null, false);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, true);
                        mutuoDados(null, tipoCabo2dois, true);
                        mutuoDados(null, tipoCabo3, true);
                        mutuoDados(null, tipoCabo4, false);
                        mutuoDados(null, tipoCabo5, false);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.VISIBLE);
                        mutuoDados(nome3, null, true);
                        mutuoDados(nome4, null, false);
                        mutuoDados(nome5, null, false);

                        mutuoDados(null, finalidade, true);
                        mutuoDados(null, finalidade2, true);
                        mutuoDados(null, finalidade3, true);
                        mutuoDados(null, finalidade4, false);
                        mutuoDados(null, finalidade5, false);

                        mutuoDados(null, ceans, true);
                        mutuoDados(null, ceans2, true);
                        mutuoDados(null, ceans3, true);
                        mutuoDados(null, ceans4, false);
                        mutuoDados(null, ceans5, false);

                        mutuoDados(null, tar, true);
                        mutuoDados(null, tar2, true);
                        mutuoDados(null, tar3, true);
                        mutuoDados(null, tar4, false);
                        mutuoDados(null, tar5, false);

                        mutuoDados(null, reservaTec, true);
                        mutuoDados(null, reservaTec2, true);
                        mutuoDados(null, reservaTec3, true);
                        mutuoDados(null, reservaTec4, false);
                        mutuoDados(null, reservaTec5, false);

                        mutuoDados(null, backbone, true);
                        mutuoDados(null, backbone2, true);
                        mutuoDados(null, backbone3, true);
                        mutuoDados(null, backbone4, false);
                        mutuoDados(null, backbone5, false);

                        mutuoDados(descricaoIrregularidade, null, true);
                        mutuoDados(descricaoIrregularidade2, null, true);
                        mutuoDados(descricaoIrregularidade3, null, true);
                        mutuoDados(descricaoIrregularidade4, null, false);
                        mutuoDados(descricaoIrregularidade5, null, false);

                        mutuoDados(observacaoMutuo, null, true);
                        mutuoDados(observacaoMutuo2, null, true);
                        mutuoDados(observacaoMutuo3, null, true);
                        mutuoDados(observacaoMutuo4, null, false);
                        mutuoDados(observacaoMutuo5, null, false);


                        placaIdentificadora.setEnabled(true);

                        placaIdentificadora2.setEnabled(true);

                        placaIdentificadora3.setEnabled(true);
                        placaIdentificadora4.setChecked(false);
                        placaIdentificadora4.setEnabled(false);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);


                        descidaCabos.setEnabled(true);

                        descidaCabos2.setEnabled(true);

                        descidaCabos3.setEnabled(true);
                        descidaCabos4.setChecked(false);
                        descidaCabos4.setEnabled(false);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        placaIdentificadora.setVisibility(View.VISIBLE);
                        placaIdentificadora2.setVisibility(View.VISIBLE);
                        placaIdentificadora3.setVisibility(View.VISIBLE);
                        placaIdentificadora4.setVisibility(View.GONE);
                        placaIdentificadora5.setVisibility(View.GONE);
                        descidaCabos.setVisibility(View.VISIBLE);
                        descidaCabos2.setVisibility(View.VISIBLE);
                        descidaCabos3.setVisibility(View.VISIBLE);
                        descidaCabos4.setVisibility(View.GONE);
                        descidaCabos5.setVisibility(View.GONE);

                        mutuo2.setVisibility(View.VISIBLE);
                        mutuo3.setVisibility(View.VISIBLE);
                        mutuo4.setVisibility(View.GONE);
                        mutuo5.setVisibility(View.GONE);

                        foto15.setVisibility(View.VISIBLE);
                        btnFoto15.setVisibility(View.VISIBLE);
                        btnUpload15.setVisibility(View.VISIBLE);
                        foto16.setVisibility(View.VISIBLE);
                        btnFoto16.setVisibility(View.VISIBLE);
                        btnUpload16.setVisibility(View.VISIBLE);
                        foto17.setVisibility(View.VISIBLE);
                        btnFoto17.setVisibility(View.VISIBLE);
                        btnUpload17.setVisibility(View.VISIBLE);

                        //endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(4).toString())) {
                        Log.i("Spinenr", "Apertei 4");
                        //region MutuoDados4

                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, true);
                        mutuoDados(quantidadeCabos2dois, null, true);
                        mutuoDados(quantidadeCabos3, null, true);
                        mutuoDados(quantidadeCabos4, null, true);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, true);
                        mutuoDados(null, tipoCabo2dois, true);
                        mutuoDados(null, tipoCabo3, true);
                        mutuoDados(null, tipoCabo4, true);
                        mutuoDados(null, tipoCabo5, false);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.VISIBLE);
                        mutuoDados(nome3, null, true);
                        mutuoDados(nome4, null, true);
                        mutuoDados(nome5, null, false);

                        mutuoDados(null, finalidade, true);
                        mutuoDados(null, finalidade2, true);
                        mutuoDados(null, finalidade3, true);
                        mutuoDados(null, finalidade4, true);
                        mutuoDados(null, finalidade5, false);

                        mutuoDados(null, ceans, true);
                        mutuoDados(null, ceans2, true);
                        mutuoDados(null, ceans3, true);
                        mutuoDados(null, ceans4, true);
                        mutuoDados(null, ceans5, false);

                        mutuoDados(null, tar, true);
                        mutuoDados(null, tar2, true);
                        mutuoDados(null, tar3, true);
                        mutuoDados(null, tar4, true);
                        mutuoDados(null, tar5, false);

                        mutuoDados(null, reservaTec, true);
                        mutuoDados(null, reservaTec2, true);
                        mutuoDados(null, reservaTec3, true);
                        mutuoDados(null, reservaTec4, true);
                        mutuoDados(null, reservaTec5, false);

                        mutuoDados(null, backbone, true);
                        mutuoDados(null, backbone2, true);
                        mutuoDados(null, backbone3, true);
                        mutuoDados(null, backbone4, true);
                        mutuoDados(null, backbone5, false);

                        mutuoDados(descricaoIrregularidade, null, true);
                        mutuoDados(descricaoIrregularidade2, null, true);
                        mutuoDados(descricaoIrregularidade3, null, true);
                        mutuoDados(descricaoIrregularidade4, null, true);
                        mutuoDados(descricaoIrregularidade5, null, false);

                        mutuoDados(observacaoMutuo, null, true);
                        mutuoDados(observacaoMutuo2, null, true);
                        mutuoDados(observacaoMutuo3, null, true);
                        mutuoDados(observacaoMutuo4, null, true);
                        mutuoDados(observacaoMutuo5, null, false);


                        placaIdentificadora.setEnabled(true);

                        placaIdentificadora2.setEnabled(true);

                        placaIdentificadora3.setEnabled(true);

                        placaIdentificadora4.setEnabled(true);
                        placaIdentificadora5.setChecked(false);
                        placaIdentificadora5.setEnabled(false);


                        descidaCabos.setEnabled(true);

                        descidaCabos2.setEnabled(true);

                        descidaCabos3.setEnabled(true);

                        descidaCabos4.setEnabled(true);
                        descidaCabos5.setChecked(false);
                        descidaCabos5.setEnabled(false);

                        placaIdentificadora.setVisibility(View.VISIBLE);
                        placaIdentificadora2.setVisibility(View.VISIBLE);
                        placaIdentificadora3.setVisibility(View.VISIBLE);
                        placaIdentificadora4.setVisibility(View.VISIBLE);
                        placaIdentificadora5.setVisibility(View.GONE);
                        descidaCabos.setVisibility(View.VISIBLE);
                        descidaCabos2.setVisibility(View.VISIBLE);
                        descidaCabos3.setVisibility(View.VISIBLE);
                        descidaCabos4.setVisibility(View.VISIBLE);
                        descidaCabos5.setVisibility(View.GONE);

                        mutuo2.setVisibility(View.VISIBLE);
                        mutuo3.setVisibility(View.VISIBLE);
                        mutuo4.setVisibility(View.VISIBLE);
                        mutuo5.setVisibility(View.GONE);

                        foto15.setVisibility(View.VISIBLE);
                        btnFoto15.setVisibility(View.VISIBLE);
                        btnUpload15.setVisibility(View.VISIBLE);
                        foto16.setVisibility(View.VISIBLE);
                        btnFoto16.setVisibility(View.VISIBLE);
                        btnUpload16.setVisibility(View.VISIBLE);
                        foto17.setVisibility(View.VISIBLE);
                        btnFoto17.setVisibility(View.VISIBLE);
                        btnUpload17.setVisibility(View.VISIBLE);

                        //endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(5).toString())) {
                        Log.i("Spinenr", "Apertei 5");
                        Log.i("Spinenr", "FUI3");
                        //region MutuoDados5

                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, true);
                        mutuoDados(quantidadeCabos2dois, null, true);
                        mutuoDados(quantidadeCabos3, null, true);
                        mutuoDados(quantidadeCabos4, null, true);
                        mutuoDados(quantidadeCabos5, null, true);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, true);
                        mutuoDados(null, tipoCabo2dois, true);
                        mutuoDados(null, tipoCabo3, true);
                        mutuoDados(null, tipoCabo4, true);
                        mutuoDados(null, tipoCabo5, true);

                        nome.setVisibility(View.VISIBLE);
                        nome2.setVisibility(View.VISIBLE);
                        mutuoDados(nome3, null, true);
                        mutuoDados(nome4, null, true);
                        mutuoDados(nome5, null, true);

                        mutuoDados(null, finalidade, true);
                        mutuoDados(null, finalidade2, true);
                        mutuoDados(null, finalidade3, true);
                        mutuoDados(null, finalidade4, true);
                        mutuoDados(null, finalidade5, true);

                        mutuoDados(null, ceans, true);
                        mutuoDados(null, ceans2, true);
                        mutuoDados(null, ceans3, true);
                        mutuoDados(null, ceans4, true);
                        mutuoDados(null, ceans5, true);

                        mutuoDados(null, tar, true);
                        mutuoDados(null, tar2, true);
                        mutuoDados(null, tar3, true);
                        mutuoDados(null, tar4, true);
                        mutuoDados(null, tar5, true);

                        mutuoDados(null, reservaTec, true);
                        mutuoDados(null, reservaTec2, true);
                        mutuoDados(null, reservaTec3, true);
                        mutuoDados(null, reservaTec4, true);
                        mutuoDados(null, reservaTec5, true);

                        mutuoDados(null, backbone, true);
                        mutuoDados(null, backbone2, true);
                        mutuoDados(null, backbone3, true);
                        mutuoDados(null, backbone4, true);
                        mutuoDados(null, backbone5, true);

                        mutuoDados(descricaoIrregularidade, null, true);
                        mutuoDados(descricaoIrregularidade2, null, true);
                        mutuoDados(descricaoIrregularidade3, null, true);
                        mutuoDados(descricaoIrregularidade4, null, true);
                        mutuoDados(descricaoIrregularidade5, null, true);

                        mutuoDados(observacaoMutuo, null, true);
                        mutuoDados(observacaoMutuo2, null, true);
                        mutuoDados(observacaoMutuo3, null, true);
                        mutuoDados(observacaoMutuo4, null, true);
                        mutuoDados(observacaoMutuo5, null, true);


                        placaIdentificadora.setEnabled(true);

                        placaIdentificadora2.setEnabled(true);

                        placaIdentificadora3.setEnabled(true);

                        placaIdentificadora4.setEnabled(true);

                        placaIdentificadora5.setEnabled(true);


                        descidaCabos.setEnabled(true);

                        descidaCabos2.setEnabled(true);

                        descidaCabos3.setEnabled(true);

                        descidaCabos4.setEnabled(true);

                        descidaCabos5.setEnabled(true);

                        placaIdentificadora.setVisibility(View.VISIBLE);
                        placaIdentificadora2.setVisibility(View.VISIBLE);
                        placaIdentificadora3.setVisibility(View.VISIBLE);
                        placaIdentificadora4.setVisibility(View.VISIBLE);
                        placaIdentificadora5.setVisibility(View.VISIBLE);
                        descidaCabos.setVisibility(View.VISIBLE);
                        descidaCabos2.setVisibility(View.VISIBLE);
                        descidaCabos3.setVisibility(View.VISIBLE);
                        descidaCabos4.setVisibility(View.VISIBLE);
                        descidaCabos5.setVisibility(View.VISIBLE);

                        mutuo2.setVisibility(View.VISIBLE);
                        mutuo3.setVisibility(View.VISIBLE);
                        mutuo4.setVisibility(View.VISIBLE);
                        mutuo5.setVisibility(View.VISIBLE);

                        foto15.setVisibility(View.VISIBLE);
                        btnFoto15.setVisibility(View.VISIBLE);
                        btnUpload15.setVisibility(View.VISIBLE);
                        foto16.setVisibility(View.VISIBLE);
                        btnFoto16.setVisibility(View.VISIBLE);
                        btnUpload16.setVisibility(View.VISIBLE);
                        foto17.setVisibility(View.VISIBLE);
                        btnFoto17.setVisibility(View.VISIBLE);
                        btnUpload17.setVisibility(View.VISIBLE);

                        //endregion
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(requireContext(),R.style.LightDialogTheme);
                progressDialog.setMessage("Salvando..."); // Setting Message
                progressDialog.setTitle("Por favor Espere"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                if((imgPath == null) && (imgPath2 == null) && (imgPath3 == null)){
                    Toast.makeText(requireContext(), "Preencha pelo menos uma foto", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else if (codigo.getText().toString().equals("") || codigo == null){
                    Toast.makeText(requireContext(), "Preencha o campo de código", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else{
                FormularioDAO formularioDAO = new FormularioDAO(requireActivity().getApplicationContext());
                final Formulario formulario = new Formulario();
                    formulario.setCodigo(Objects.requireNonNull(codigo.getText()).toString());
                if (imgPath != null) {
                    formulario.setCaminhoImagem(imgPath);
                } else {
                    formulario.setCaminhoImagem("");
                }
                if (imgPath2 != null) {
                    formulario.setCaminhoImagem2(imgPath2);
                } else {
                    formulario.setCaminhoImagem2("");
                }
                if (imgPath3 != null) {
                    formulario.setCaminhoImagem3(imgPath3);
                } else {
                    formulario.setCaminhoImagem3("");
                }

                try {
                    Log.i("URL", urlFoto.toString());
                }catch (Exception e){

                }
                if((urlFoto == null) || ((urlFoto.toString()).equals(""))) {
                    formulario.setUrlImagem("");
                    formulario.setColor(String.valueOf(R.color.design_default_color_error));
                }else {
                    formulario.setUrlImagem(urlFoto.toString());
                    formulario.setColor(String.valueOf(R.color.colorPrimary));
                }
                if((urlFoto2 == null) || ((urlFoto2.toString()).equals(""))) {
                    formulario.setUrlImagem2("");
                    formulario.setColor2(String.valueOf(R.color.design_default_color_error));
                }else {
                    formulario.setUrlImagem2(urlFoto2.toString());
                    formulario.setColor2(String.valueOf(R.color.colorPrimary));
                }
                if((urlFoto3 == null) || ((urlFoto3.toString()).equals(""))) {
                    formulario.setUrlImagem3("");
                    formulario.setColor3(String.valueOf(R.color.design_default_color_error));
                }else {
                    formulario.setUrlImagem3(urlFoto3.toString());
                    formulario.setColor3(String.valueOf(R.color.colorPrimary));
                }


                //LOCALIZAÇÂO

                formulario.setEndereco(Objects.requireNonNull(endereco.getText()).toString());
                setLista(formulario,municipio, "municipio");
                formulario.setLatitude(Objects.requireNonNull(latitude.getText()).toString());
                formulario.setLongitude(Objects.requireNonNull(longitude.getText()).toString());
                setLista(formulario, alturaCarga, "alturaCarga");


                //CARACTERISTICAS FÍSICAS

                setLista(formulario,tipoPoste, "tipoPoste");
                if (normal.isChecked()) {
                    formulario.setNormal("Sim");
                } else {
                    formulario.setNormal("Não");
                }
                if (ferragemExposta.isChecked()) {
                    formulario.setFerragemExposta("Sim");
                } else {
                    formulario.setFerragemExposta("Não");
                }
                if (fletido.isChecked()) {
                    formulario.setFletido("Sim");
                } else {
                    formulario.setFletido("Não");
                }
                if (danificado.isChecked()) {
                    formulario.setDanificado("Sim");
                } else {
                    formulario.setDanificado("Não");
                }
                if (abalroado.isChecked()) {
                    formulario.setAbalroado("Sim");
                } else {
                    formulario.setAbalroado("Não");
                }
                if (trincado.isChecked()) {
                    formulario.setTrincado("Sim");
                } else {
                    formulario.setTrincado("Não");
                }
                formulario.setObservacaoFisicas(Objects.requireNonNull(observacaoFisicas.getText()).toString());


                //ILUMINAÇÃO

                if (ip.isChecked()) {
                    formulario.setIp("Sim");
                } else {
                    formulario.setIp("Não");
                }
                setLista(formulario,ipEstrutura,"ipEstrutura");
                formulario.setQuantidadeLampada(Objects.requireNonNull(quantidadeLampada.getText().toString()));
                setLista(formulario, tipoPot, "tipoPot");
                formulario.setPotReator(Objects.requireNonNull(potReator.getText()).toString());
                setLista(formulario, ipAtivacao, "ipAtivacao");
                if (vinteEQuatro.isChecked()) {
                    formulario.setVinteEQuatro("Sim");
                } else {
                    formulario.setVinteEQuatro("Não");
                }
                formulario.setQuantidade24H(Objects.requireNonNull(quantidade24H.getText().toString()));

                if (ip2.isChecked()) {
                    formulario.setIp2("Sim");
                } else {
                    formulario.setIp2("Não");
                }
                setLista(formulario,ipEstrutura2,"ipEstrutura2");
                formulario.setQuantidadeLampada2(Objects.requireNonNull(quantidadeLampada2.getText().toString()));
                setLista(formulario, tipoPot2, "tipoPot2");
                formulario.setPotReator2(Objects.requireNonNull(potReator2.getText()).toString());
                setLista(formulario, ipAtivacao2, "ipAtivacao2");
                if (vinteEQuatro2.isChecked()) {
                    formulario.setVinteEQuatro2("Sim");
                } else {
                    formulario.setVinteEQuatro2("Não");
                }
                formulario.setQuantidade24H2(Objects.requireNonNull(quantidade24H2.getText().toString()));

                if (ip3.isChecked()) {
                    formulario.setIp3("Sim");
                } else {
                    formulario.setIp3("Não");
                }
                setLista(formulario,ipEstrutura3,"ipEstrutura3");
                formulario.setQuantidadeLampada3(Objects.requireNonNull(quantidadeLampada3.getText().toString()));
                setLista(formulario, tipoPot3, "tipoPot3");
                formulario.setPotReator3(Objects.requireNonNull(potReator3.getText()).toString());
                setLista(formulario, ipAtivacao3, "ipAtivacao3");
                if (vinteEQuatro3.isChecked()) {
                    formulario.setVinteEQuatro3("Sim");
                } else {
                    formulario.setVinteEQuatro3("Não");
                }
                formulario.setQuantidade24H3(Objects.requireNonNull(quantidade24H3.getText().toString()));
                formulario.setObservacaoIP(Objects.requireNonNull(observacaoIP.getText().toString()));


                //TRAFO

                if (ativos.isChecked()) {
                    formulario.setAtivos("Sim");
                } else {
                    formulario.setAtivos("Não");
                }
                if (chkTrafoTrifasico.isChecked()) {
                    formulario.setChkTrafoTrifasico("Sim");
                } else {
                    formulario.setChkTrafoTrifasico("Não");
                }
                if (chkTrafoMono.isChecked()) {
                    formulario.setChkTrafoMono("Sim");
                } else {
                    formulario.setChkTrafoMono("Não");
                }
                setLista(formulario,trafoTrifasico,"trafoTrifasico");
                setLista(formulario,trafoMono,"trafoMono");
                if (religador.isChecked()) {
                    formulario.setReligador("Sim");
                } else {
                    formulario.setReligador("Não");
                }
                if (medicao.isChecked()) {
                    formulario.setMedicao("Sim");
                } else {
                    formulario.setMedicao("Não");
                }
                if (chFusivel.isChecked()) {
                    formulario.setChFusivel("Sim");
                } else {
                    formulario.setChFusivel("Não");
                }
                if (chFaca.isChecked()) {
                    formulario.setChFaca("Sim");
                } else {
                    formulario.setChFaca("Não");
                }
                if (chBanco.isChecked()) {
                    formulario.setBanco("Sim");
                } else {
                    formulario.setBanco("Não");
                }
                if (chFusivelReligador.isChecked()) {
                    formulario.setChFusivelReligador("Sim");
                } else {
                    formulario.setChFusivelReligador("Não");
                }
                setLista(formulario,ramalSubt,"ramalSubt");
                formulario.setObservacaoAtivos(Objects.requireNonNull(observacaoAtivos.getText()).toString());
                formulario.setOutros(Objects.requireNonNull(outros.getText()).toString());

                //MUTUO
                if (mutuo.isChecked()) {
                    formulario.setMutuo("Sim");
                } else {
                    formulario.setMutuo("Não");
                }
                setLista(formulario,quantidadeOcupantes,"quantidadeOcupantes");

                formulario.setQuantidadeCabos(Objects.requireNonNull(quantidadeCabos.getText()).toString());
                setLista(formulario,tipoCabo,"tipoCabo");
                formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                setLista(formulario,finalidade,"finalidade");
                setLista(formulario,ceans,"ceans");
                setLista(formulario,tar,"tar");
                setLista(formulario,reservaTec,"reservaTec");
                setLista(formulario,backbone,"backbone");
                if(placaIdentificadora.isChecked()){
                    formulario.setPlacaIdent("Sim");
                }else {
                    formulario.setPlacaIdent("Não");
                }

                if(descidaCabos.isChecked()){
                    formulario.setDescidaCabos("Sim");
                }else {
                    formulario.setDescidaCabos("Não");
                }
                formulario.setDescricaoIrregularidade(Objects.requireNonNull(descricaoIrregularidade.getText()).toString());
                formulario.setObservacaoMutuo(Objects.requireNonNull(observacaoMutuo.getText()).toString());

                formulario.setQuantidadeCabos2(Objects.requireNonNull(quantidadeCabos2.getText()).toString());
                setLista(formulario,tipoCabo2,"tipoCabo2");
                    formulario.setQuantidadeCabos2dois(Objects.requireNonNull(quantidadeCabos2dois.getText()).toString());
                    setLista(formulario,tipoCabo2dois,"tipoCabo2dois");
                formulario.setNome2(Objects.requireNonNull(nome2.getText()).toString());
                setLista(formulario,finalidade2,"finalidade2");
                setLista(formulario,ceans2,"ceans2");
                setLista(formulario,tar2,"tar2");
                setLista(formulario,reservaTec2,"reservaTec2");
                setLista(formulario,backbone2,"backbone2");
                if(placaIdentificadora2.isChecked()){
                    formulario.setPlacaIdent2("Sim");
                }else {
                    formulario.setPlacaIdent2("Não");
                }

                if(descidaCabos2.isChecked()){
                    formulario.setDescidaCabos2("Sim");
                }else {
                    formulario.setDescidaCabos2("Não");
                }
                formulario.setDescricaoIrregularidade2(Objects.requireNonNull(descricaoIrregularidade2.getText()).toString());
                formulario.setObservacaoMutuo2(Objects.requireNonNull(observacaoMutuo2.getText()).toString());

                formulario.setQuantidadeCabos3(Objects.requireNonNull(quantidadeCabos3.getText()).toString());
                setLista(formulario,tipoCabo3,"tipoCabo3");
                formulario.setNome3(Objects.requireNonNull(nome3.getText()).toString());
                setLista(formulario,finalidade3,"finalidade3");
                setLista(formulario,ceans3,"ceans3");
                setLista(formulario,tar3,"tar3");
                setLista(formulario,reservaTec3,"reservaTec3");
                setLista(formulario,backbone3,"backbone3");
                if(placaIdentificadora3.isChecked()){
                    formulario.setPlacaIdent3("Sim");
                }else {
                    formulario.setPlacaIdent3("Não");
                }

                if(descidaCabos3.isChecked()){
                    formulario.setDescidaCabos3("Sim");
                }else {
                    formulario.setDescidaCabos3("Não");
                }
                formulario.setDescricaoIrregularidade3(Objects.requireNonNull(descricaoIrregularidade3.getText()).toString());
                formulario.setObservacaoMutuo3(Objects.requireNonNull(observacaoMutuo3.getText()).toString());

                formulario.setQuantidadeCabos4(Objects.requireNonNull(quantidadeCabos4.getText()).toString());
                setLista(formulario,tipoCabo4,"tipoCabo4");
                formulario.setNome4(Objects.requireNonNull(nome4.getText()).toString());
                setLista(formulario,finalidade4,"finalidade4");
                setLista(formulario,ceans4,"ceans4");
                setLista(formulario,tar4,"tar4");
                setLista(formulario,reservaTec4,"reservaTec4");
                setLista(formulario,backbone4,"backbone4");
                if(placaIdentificadora4.isChecked()){
                    formulario.setPlacaIdent4("Sim");
                }else {
                    formulario.setPlacaIdent4("Não");
                }

                if(descidaCabos4.isChecked()){
                    formulario.setDescidaCabos4("Sim");
                }else {
                    formulario.setDescidaCabos4("Não");
                }
                formulario.setDescricaoIrregularidade4(Objects.requireNonNull(descricaoIrregularidade4.getText()).toString());
                formulario.setObservacaoMutuo4(Objects.requireNonNull(observacaoMutuo4.getText()).toString());

                formulario.setQuantidadeCabos5(Objects.requireNonNull(quantidadeCabos5.getText()).toString());
                setLista(formulario,tipoCabo5,"tipoCabo5");
                formulario.setNome5(Objects.requireNonNull(nome5.getText()).toString());
                setLista(formulario,finalidade5,"finalidade5");
                setLista(formulario,ceans5,"ceans5");
                setLista(formulario,tar5,"tar5");
                setLista(formulario,reservaTec5,"reservaTec5");
                setLista(formulario,backbone5,"backbone5");
                if(placaIdentificadora5.isChecked()){
                    formulario.setPlacaIdent5("Sim");
                }else {
                    formulario.setPlacaIdent5("Não");
                }

                if(descidaCabos5.isChecked()){
                    formulario.setDescidaCabos5("Sim");
                }else {
                    formulario.setDescidaCabos5("Não");
                }
                formulario.setDescricaoIrregularidade5(Objects.requireNonNull(descricaoIrregularidade5.getText()).toString());
                formulario.setObservacaoMutuo5(Objects.requireNonNull(observacaoMutuo5.getText()).toString());
                //VEGETAÇÃO
                setLista(formulario,dimensaoVegetacao,"dimensaoVegetacao");
                setLista(formulario,distaciaBaixa,"distanciaBaixa");
                setLista(formulario,distanciaMedia,"distanciaMedia");
                setLista(formulario,estadoArvore,"estadoArvore");
                if(quedaArvore.isChecked()){
                    formulario.setQuedaArvore("Sim");
                }else {
                    formulario.setQuedaArvore("Não");
                }
                setLista(formulario,localArvore,"localArvore");
                formulario.setObservacaoVegetacao(Objects.requireNonNull(observacaoVegetacao.getText()).toString());
                formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                while(formulario.getUrlImagem() == null){
                    new CountDownTimer(2000,1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {

                        }
                    };
                }
                if (formularioAtual != null) {
                    formulario.setId(formularioAtual.getId());
                    formulario.setData(formularioAtual.getData());
                    if (formularioDAO.atualizar(formulario)) {
                        CadastradosFragment cadastradosFragment = new CadastradosFragment();

                        FragmentManager fm = getParentFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                        transaction.commit();
                        Toast.makeText(requireActivity().getApplicationContext(), "Sucesso ao atualizar formulário", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(requireActivity().getApplicationContext(), "Erro ao atualizar formulário", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                } else {
                    String thisDayText, thisMonthText, thisYearText;
                    //region Inicialização da data
                    Calendar calendar = Calendar.getInstance();

                    int thisYear = calendar.get(Calendar.YEAR);
                    thisYearText = String.valueOf(thisYear);

                    int thisMonth = calendar.get(Calendar.MONTH) + 1;
                    if (thisMonth < 9) {
                        thisMonthText = "0" + thisMonth;
                    } else {
                        thisMonthText = String.valueOf(thisMonth);
                    }

                    int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
                    if (thisDay < 9) {
                        thisDayText = "0" + thisDay;
                    } else {
                        thisDayText = String.valueOf(thisDay);
                    }
                    String data = thisDayText + "/" + thisMonthText + "/" + thisYearText;
                    String timeStamp = new SimpleDateFormat("dd/MM/yy-HH:mm:ss").format(new Date());
                    SimpleDateFormat readDate = new SimpleDateFormat("dd/MM/yy-HH:mm:ss");
                    readDate.setTimeZone(TimeZone.getTimeZone("GMT"));
                    Date date = null;
                    try {
                        date = readDate.parse(timeStamp);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat writeDate = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
                    writeDate.setTimeZone(TimeZone.getTimeZone("GMT-04:00"));
                    String s = writeDate.format(date);

                    //endregion
                    formulario.setData(s);
                    if (formularioDAO.salvar(formulario)) {
                        CadastradosFragment cadastradosFragment = new CadastradosFragment();

                        FragmentManager fm = getParentFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                        transaction.commit();
                        Toast.makeText(requireActivity().getApplicationContext(), "Sucesso ao salvar formulário", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(requireActivity().getApplicationContext(), "Erro ao salvar formulário", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }}
        });
       if(savedInstanceState != null) {
           imgPath = savedInstanceState.getString("imgPath");
           imagem = BitmapFactory.decodeFile(imgPath);
       }
        return root;
    }
//Pega o valor do spinner e coloca um "-" caso, o usuário não tenha escolhido nenhuma opção.
    public void setLista(Formulario formulario, Spinner spinner, String atributo){
        if(spinner.getSelectedItem().toString().equals(spinner.getItemAtPosition(0).toString())){
            formulario.GenericSetter(atributo,"-");
        }else{
            formulario.GenericSetter(atributo,spinner.getSelectedItem().toString());
        }
    }

    public void mutuoDados(EditText editText, Spinner spinner, Boolean estado){
        if((editText != null) && !estado){
            editText.setText("");
            editText.setEnabled(estado);
            editText.setVisibility(View.GONE);

        }
        else if((editText != null) && estado){
            editText.setEnabled(estado);
            editText.setVisibility(View.VISIBLE);

        }
        else if ((spinner != null) && !estado) {
            spinner.setSelection(0);
            spinner.setEnabled(estado);
            spinner.setVisibility(View.GONE);
        }
        else if ((spinner != null) && estado) {
            spinner.setEnabled(estado);
            spinner.setVisibility(View.VISIBLE);
        }

    }
    public Boolean verificarPermissaoLocaliza() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(requireActivity().getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireActivity().getApplicationContext(),
                permissions[1]) == PackageManager.PERMISSION_GRANTED
        ){
            return true;


        }else{
            ActivityCompat.requestPermissions(requireActivity(),permissions,REQUEST_CODE);
            return false;
        }
    }






    public Boolean verificarPermissao() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA};

        if(ContextCompat.checkSelfPermission(requireActivity().getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireActivity().getApplicationContext(),
                permissions[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireActivity().getApplicationContext(),
                permissions[2]) == PackageManager.PERMISSION_GRANTED
        ){
                return true;


        }else{
            ActivityCompat.requestPermissions(requireActivity(),permissions,REQUEST_CODE);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verificarPermissao();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("CODE", String.valueOf(requestCode));
        if(resultCode == RESULT_OK){
            Uri localImagemSelecionada;
            Cursor cursor;
            int columnIndex;


            try{
                switch (requestCode){
                    case IMAGE_CAPTURE_CODE:
                        imgPath = photoFile.getAbsolutePath();
                        Log.i("TAHA", imgPath);
                        imagem = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto.setImageBitmap(imagem);
                        break;
                    case IMAGE_PICK_CODE:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto.setImageBitmap(imagem);
                        String[] filePathColumn = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imgPath = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto.setImageBitmap(imagem);
                        break;
                    case IMAGE_CAPTURE_CODE2:
                        imgPath2 = photoFile.getAbsolutePath();
                        imagem2 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto2.setImageBitmap(imagem2);
                        break;
                    case IMAGE_PICK_CODE2:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem2 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto2.setImageBitmap(imagem2);
                        Log.i("ERROAntes", imagem2.toString());
                        String[] filePathColumn2 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn2, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn2[0]);
                        imgPath2 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto2.setImageBitmap(imagem2);
                        break;

                    case IMAGE_CAPTURE_CODE3:
                        imgPath3 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath3);
                        imagem3 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto3.setImageBitmap(imagem3);
                        break;
                    case IMAGE_PICK_CODE3:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem3 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto3.setImageBitmap(imagem3);
                        String[] filePathColumn3 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn3, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn3[0]);
                        imgPath3 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto3.setImageBitmap(imagem3);
                        break;

                    case IMAGE_CAPTURE_CODE4:
                        imgPath4 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath4);
                        imagem4 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto4.setImageBitmap(imagem4);
                        break;
                    case IMAGE_PICK_CODE4:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem4 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto4.setImageBitmap(imagem4);
                        String[] filePathColumn4 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn4, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn4[0]);
                        imgPath4 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto4.setImageBitmap(imagem4);
                        break;

                    case IMAGE_CAPTURE_CODE5:
                        imgPath5 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath5);
                        imagem5 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto5.setImageBitmap(imagem5);
                        break;
                    case IMAGE_PICK_CODE5:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem5 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto5.setImageBitmap(imagem5);
                        String[] filePathColumn5 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn5, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn5[0]);
                        imgPath5 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto5.setImageBitmap(imagem5);
                        break;

                    case IMAGE_CAPTURE_CODE6:
                        imgPath6 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath6);
                        imagem6 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto6.setImageBitmap(imagem6);
                        break;
                    case IMAGE_PICK_CODE6:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem6 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto6.setImageBitmap(imagem6);
                        String[] filePathColumn6 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn6, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn6[0]);
                        imgPath6 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto6.setImageBitmap(imagem6);
                        break;

                    case IMAGE_CAPTURE_CODE7:
                        imgPath7 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath7);
                        imagem7 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto7.setImageBitmap(imagem7);
                        break;
                    case IMAGE_PICK_CODE7:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem7 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto7.setImageBitmap(imagem7);
                        String[] filePathColumn7 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn7, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn7[0]);
                        imgPath7 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto7.setImageBitmap(imagem7);
                        break;

                    case IMAGE_CAPTURE_CODE8:
                        imgPath8 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath8);
                        imagem8 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto8.setImageBitmap(imagem8);
                        break;
                    case IMAGE_PICK_CODE8:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem8 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto8.setImageBitmap(imagem8);
                        String[] filePathColumn8 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn8, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn8[0]);
                        imgPath8 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto8.setImageBitmap(imagem8);
                        break;

                    case IMAGE_CAPTURE_CODE9:
                        imgPath9 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath9);
                        imagem9 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto9.setImageBitmap(imagem9);
                        break;
                    case IMAGE_PICK_CODE9:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem9 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        foto9.setImageBitmap(imagem9);
                        String[] filePathColumn9 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn9, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn9[0]);
                        imgPath9 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto9.setImageBitmap(imagem9);
                        break;

                    case IMAGE_CAPTURE_CODE10:
                        imgPath10 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath10);
                        imagem10 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto10.setImageBitmap(imagem10);
                        break;
                    case IMAGE_PICK_CODE10:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem10 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto10.setImageBitmap(imagem10);
                        String[] filePathColumn10 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn10, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn10[0]);
                        imgPath10 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto10.setImageBitmap(imagem10);
                        break;

                    case IMAGE_CAPTURE_CODE11:
                        imgPath11 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath11);
                        imagem11 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto11.setImageBitmap(imagem11);
                        break;
                    case IMAGE_PICK_CODE11:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem11 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto11.setImageBitmap(imagem11);
                        String[] filePathColumn11 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn11, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn11[0]);
                        imgPath11 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto11.setImageBitmap(imagem11);
                        break;

                    case IMAGE_CAPTURE_CODE12:
                        imgPath12 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath12);
                        imagem12 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto12.setImageBitmap(imagem12);
                        break;
                    case IMAGE_PICK_CODE12:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem12 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto12.setImageBitmap(imagem12);
                        String[] filePathColumn12 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn12, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn12[0]);
                        imgPath12 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto12.setImageBitmap(imagem12);
                        break;
                    case IMAGE_CAPTURE_CODE13:
                        imgPath13 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath13);
                        imagem13 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto13.setImageBitmap(imagem13);
                        break;
                    case IMAGE_PICK_CODE13:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem13 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto12.setImageBitmap(imagem12);
                        String[] filePathColumn13 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn13, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn13[0]);
                        imgPath13 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto13.setImageBitmap(imagem13);
                        break;
                    case IMAGE_CAPTURE_CODE14:
                        imgPath14 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath14);
                        imagem14 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto14.setImageBitmap(imagem14);
                        break;
                    case IMAGE_PICK_CODE14:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem14 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto12.setImageBitmap(imagem12);
                        String[] filePathColumn14 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn14, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn14[0]);
                        imgPath14 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto14.setImageBitmap(imagem14);
                        break;
                    case IMAGE_CAPTURE_CODE15:
                        imgPath15 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath15);
                        imagem15 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto15.setImageBitmap(imagem15);
                        break;
                    case IMAGE_PICK_CODE15:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem15 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto12.setImageBitmap(imagem12);
                        String[] filePathColumn15 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn15, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn15[0]);
                        imgPath15 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto15.setImageBitmap(imagem15);
                        break;

                    case IMAGE_CAPTURE_CODE16:
                        imgPath16 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath16);
                        imagem16 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto16.setImageBitmap(imagem16);
                        break;
                    case IMAGE_PICK_CODE16:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem16 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto12.setImageBitmap(imagem12);
                        String[] filePathColumn16 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn16, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn16[0]);
                        imgPath16 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto16.setImageBitmap(imagem16);
                        break;
                    case IMAGE_CAPTURE_CODE17:
                        imgPath17 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath17);
                        imagem17 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto17.setImageBitmap(imagem17);
                        break;
                    case IMAGE_PICK_CODE17:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem17 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto12.setImageBitmap(imagem12);
                        String[] filePathColumn17 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn17, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn17[0]);
                        imgPath17 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto17.setImageBitmap(imagem17);
                        break;

                    case IMAGE_CAPTURE_CODE18:
                        imgPath18 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath18);
                        imagem18 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto18.setImageBitmap(imagem18);
                        break;
                    case IMAGE_PICK_CODE18:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem18 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        //foto12.setImageBitmap(imagem12);
                        String[] filePathColumn18 = { MediaStore.Images.Media.DATA };
                        // Get the cursor
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn18, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        columnIndex = cursor.getColumnIndex(filePathColumn18[0]);
                        imgPath18 = cursor.getString(columnIndex);
                        cursor.close();
                        // Set the Image in ImageView after decoding the String
                        foto18.setImageBitmap(imagem18);
                        break;
                    case IMAGE_CAPTURE_CODE19:
                        imgPath19 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath19);
                        imagem19 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto19.setImageBitmap(imagem19);
                        break;
                    case IMAGE_PICK_CODE19:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem19 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn19 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn19, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn19[0]);
                        imgPath19 = cursor.getString(columnIndex);
                        cursor.close();
                        foto19.setImageBitmap(imagem19);
                        break;
                    case IMAGE_CAPTURE_CODE20:
                        imgPath20 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath20);
                        imagem20 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto20.setImageBitmap(imagem20);
                        break;
                    case IMAGE_PICK_CODE20:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem20 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn20 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn20, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn20[0]);
                        imgPath20 = cursor.getString(columnIndex);
                        cursor.close();
                        foto20.setImageBitmap(imagem20);
                        break;
                    case IMAGE_CAPTURE_CODE21:
                        imgPath21 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath21);
                        imagem21 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto21.setImageBitmap(imagem21);
                        break;
                    case IMAGE_PICK_CODE21:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem21 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn21 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn21, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn21[0]);
                        imgPath21 = cursor.getString(columnIndex);
                        cursor.close();
                        foto21.setImageBitmap(imagem21);
                        break;
                    case IMAGE_CAPTURE_CODE22:
                        imgPath22 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath22);
                        imagem22 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto22.setImageBitmap(imagem22);
                        break;
                    case IMAGE_PICK_CODE22:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem22 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn22 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn22, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn22[0]);
                        imgPath22 = cursor.getString(columnIndex);
                        cursor.close();
                        foto22.setImageBitmap(imagem22);
                        break;
                    case IMAGE_CAPTURE_CODE23:
                        imgPath23 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath23);
                        imagem23 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto23.setImageBitmap(imagem23);
                        break;
                    case IMAGE_PICK_CODE23:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem23 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn23 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn23, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn23[0]);
                        imgPath23 = cursor.getString(columnIndex);
                        cursor.close();
                        foto23.setImageBitmap(imagem23);
                        break;
                    case IMAGE_CAPTURE_CODE24:
                        imgPath24 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath24);
                        imagem24 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto24.setImageBitmap(imagem24);
                        break;
                    case IMAGE_PICK_CODE24:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem24 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn24 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn24, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn24[0]);
                        imgPath24 = cursor.getString(columnIndex);
                        cursor.close();
                        foto24.setImageBitmap(imagem24);
                        break;
                    case IMAGE_CAPTURE_CODE25:
                        imgPath25 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath25);
                        imagem25 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto25.setImageBitmap(imagem25);
                        break;
                    case IMAGE_PICK_CODE25:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem25 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn25 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn25, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn25[0]);
                        imgPath25 = cursor.getString(columnIndex);
                        cursor.close();
                        foto25.setImageBitmap(imagem25);
                        break;
                    case IMAGE_CAPTURE_CODE26:
                        imgPath26 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath26);
                        imagem26 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto26.setImageBitmap(imagem26);
                        break;
                    case IMAGE_PICK_CODE26:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem26 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn26 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn26, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn26[0]);
                        imgPath26 = cursor.getString(columnIndex);
                        cursor.close();
                        foto26.setImageBitmap(imagem26);
                        break;
                    case IMAGE_CAPTURE_CODE27:
                        imgPath27 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath27);
                        imagem27 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto27.setImageBitmap(imagem27);
                        break;
                    case IMAGE_PICK_CODE27:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem27 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn27 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn27, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn27[0]);
                        imgPath27 = cursor.getString(columnIndex);
                        cursor.close();
                        foto27.setImageBitmap(imagem27);
                        break;
                    case IMAGE_CAPTURE_CODE28:
                        imgPath28 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath28);
                        imagem28 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto28.setImageBitmap(imagem28);
                        break;
                    case IMAGE_PICK_CODE28:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem28 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn28 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn28, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn28[0]);
                        imgPath28 = cursor.getString(columnIndex);
                        cursor.close();
                        foto28.setImageBitmap(imagem28);
                    case IMAGE_CAPTURE_CODE29:
                        imgPath29 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath29);
                        imagem19 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto29.setImageBitmap(imagem29);
                        break;
                    case IMAGE_PICK_CODE29:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem29 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn29 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn29, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn29[0]);
                        imgPath29 = cursor.getString(columnIndex);
                        cursor.close();
                        foto29.setImageBitmap(imagem29);
                        break;
                    case IMAGE_CAPTURE_CODE30:
                        imgPath30 = photoFile.getAbsolutePath();
                        Log.i("TAHC", imgPath30);
                        imagem30 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto30.setImageBitmap(imagem30);
                        break;
                    case IMAGE_PICK_CODE30:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem30 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        String[] filePathColumn30 = { MediaStore.Images.Media.DATA };
                        cursor = requireActivity().getApplicationContext().getContentResolver().query(localImagemSelecionada,
                                filePathColumn30, null, null, null);
                        cursor.moveToFirst();
                        columnIndex = cursor.getColumnIndex(filePathColumn30[0]);
                        imgPath30 = cursor.getString(columnIndex);
                        cursor.close();
                        foto30.setImageBitmap(imagem30);
                        break;


                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
    public void chamarGaleria(int imagemCodigo){
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(cameraIntent.resolveActivity(requireActivity().getApplicationContext().getPackageManager()) != null){
            startActivityForResult(cameraIntent, imagemCodigo);
        }
    }

    public void chamarCamera(int imagemCodigo){
         Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(requireActivity().getApplicationContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            try {

                photoFile = createImageFile();

                Log.i("TAG",photoFile.getAbsolutePath());

                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(requireActivity().getApplicationContext(),
                            "com.example.satelprojetos.provider",
                            photoFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(cameraIntent, imagemCodigo);
                }
            } catch (Exception ex) {
                // Error occurred while creating the File

            }


        }else
        {

        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        //String imgPathFile = image.getAbsolutePath();
        return image;
    }

         private boolean isNetworkAvailable() {
             ConnectivityManager connectivityManager
                     = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
             NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
             return activeNetworkInfo != null && activeNetworkInfo.isConnected();
         }


    private static Bitmap rotateImageIfRequired(Context context,Bitmap img, String filePath) throws IOException {
        ExifInterface ei;
            ei = new ExifInterface(filePath);

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
Log.i("TAGX", String.valueOf(orientation));
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:

                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:

                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:

                return rotateImage(img, 270);
            default:

                return img;
        }
    }

    public void upload(Bitmap imagemUpload,String imgPathUpload, EditText codigoUpload, int contadorUpload, final int codigoSetor, final boolean estado, String sufixo){
        if(imagemUpload == null){
            Toast.makeText(requireActivity().getApplicationContext(), "Escolha primeiro uma foto para fazer o upload", Toast.LENGTH_SHORT).show();

        }else if(codigoUpload.getText().toString().equals("") ||codigoUpload == null) {
            Toast.makeText(requireActivity().getApplicationContext(), "Insira o código do poste primeiro", Toast.LENGTH_SHORT).show();
        }else {
            if(isNetworkAvailable()) {
                progressDialog = new ProgressDialog(requireContext(),R.style.LightDialogTheme);
                progressDialog.setMessage("Fazendo upload..."); // Setting Message
                progressDialog.setTitle("Por favor Espere"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                String nomeFoto = UUID.randomUUID().toString();
                Bitmap imagemCorrigida;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    imagemCorrigida = rotateImageIfRequired(requireContext(),imagemUpload,imgPathUpload);
                } catch (IOException e) {
                    imagemCorrigida = imagemUpload;
                    e.printStackTrace();
                }

                imagemCorrigida.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                byte[] dadosImagem = baos.toByteArray();
                String pastaNome = codigoUpload.getText().toString() + "_" + sufixo + contadorUpload +".jpeg";
                final StorageReference imageRef = storageReference
                        .child("imagens")
                        .child(Base64Custom.codificarBase64(autentificacao.getCurrentUser().getEmail()))
                        .child(codigoUpload.getText().toString())
                        .child(pastaNome);
                UploadTask uploadTask = imageRef.putBytes(dadosImagem);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(requireActivity().getApplicationContext(), "Erro ao fazer upload verifique a conexão com a internet", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("ERRO", "EERO2");
                        imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                switch (codigoSetor) {
                                    case 1:
                                        urlFoto = task.getResult();
                                        novoUpload = estado;
                                        contadorPan = contadorPan+1;
                                        break;
                                    case 2:
                                        urlFoto2 = task.getResult();
                                        novoUpload2 = estado;
                                        contadorPan = contadorPan+1;
                                        break;
                                    case 3:
                                        urlFoto3 = task.getResult();
                                        novoUpload3 = estado;
                                        contadorPan = contadorPan+1;
                                        break;
                                    case 4:
                                        urlFoto4 = task.getResult();
                                        novoUpload4 = estado;
                                        contadorIp = contadorIp+1;
                                        break;
                                    case 5:
                                        urlFoto5 = task.getResult();
                                        novoUpload5 = estado;
                                        contadorIp = contadorIp+1;
                                        break;
                                    case 6:
                                        urlFoto6 = task.getResult();
                                        novoUpload6 = estado;
                                        contadorIp = contadorIp+1;
                                        break;
                                    case 7:
                                        urlFoto7 = task.getResult();
                                        novoUpload7 = estado;
                                        contadorIp = contadorIp+1;
                                        break;
                                    case 8:
                                        urlFoto8 = task.getResult();
                                        novoUpload8 = estado;
                                        contadorIp = contadorIp+1;
                                        break;
                                    case 9:
                                        urlFoto9 = task.getResult();
                                        novoUpload9 = estado;
                                        contadorIp = contadorIp+1;
                                        break;
                                    case 10:
                                        urlFoto10 = task.getResult();
                                        novoUpload10 = estado;
                                        contadorIp = contadorIp+1;
                                        break;
                                    case 11:
                                        urlFoto11 = task.getResult();
                                        novoUpload11 = estado;
                                        contadorIp = contadorIp+1;
                                        break;
                                    case 12:
                                        urlFoto12 = task.getResult();
                                        novoUpload12 = estado;
                                        contadorIp = contadorIp+1;
                                        break;
                                    case 13:
                                        urlFoto13 = task.getResult();
                                        novoUpload13 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 14:
                                        urlFoto14 = task.getResult();
                                        novoUpload14 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 15:
                                        urlFoto15 = task.getResult();
                                        novoUpload15 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 16:
                                        urlFoto16 = task.getResult();
                                        novoUpload16 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 17:
                                        urlFoto17 = task.getResult();
                                        novoUpload17 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 18:
                                        urlFoto18 = task.getResult();
                                        novoUpload18 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 19:
                                        urlFoto19 = task.getResult();
                                        novoUpload19 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 20:
                                        urlFoto20 = task.getResult();
                                        novoUpload20 = estado;
                                        contadorAt = contadorAt+1;
                                        break;

                                    case 21:
                                        urlFoto21 = task.getResult();
                                        novoUpload21 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 22:
                                        urlFoto22 = task.getResult();
                                        novoUpload22 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 23:
                                        urlFoto23 = task.getResult();
                                        novoUpload23 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 24:
                                        urlFoto24 = task.getResult();
                                        novoUpload24 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 25:
                                        urlFoto25 = task.getResult();
                                        novoUpload25 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 26:
                                        urlFoto26 = task.getResult();
                                        novoUpload26 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 27:
                                        urlFoto27 = task.getResult();
                                        novoUpload27 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 28:
                                        urlFoto28 = task.getResult();
                                        novoUpload28 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 29:
                                        urlFoto29 = task.getResult();
                                        novoUpload29 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                    case 30:
                                        urlFoto30 = task.getResult();
                                        novoUpload30 = estado;
                                        contadorAt = contadorAt+1;
                                        break;
                                }
                                progressDialog.dismiss();
                            }
                        });
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Log.i("ERRO", "EERO3");
                    }
                });
            }else {
                Toast.makeText(requireActivity().getApplicationContext(), "Erro ao fazer upload verifique a conexão com a internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("imgPath", imgPath);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

}

