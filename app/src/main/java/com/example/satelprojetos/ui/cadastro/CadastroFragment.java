package com.example.satelprojetos.ui.cadastro;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import java.net.URLStreamHandlerFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class CadastroFragment extends Fragment {
    private static final int REQUEST_CODE = 1;
    private static final int IMAGE_CAPTURE_CODE =2 ;
    private static final int IMAGE_PICK_CODE =3 ;
    private static final int IMAGE_CAPTURE_CODE2 =5 ;
    private static final int IMAGE_PICK_CODE2 =6 ;
    private static final int IMAGE_CAPTURE_CODE3 =4 ;
    private static final int IMAGE_PICK_CODE3 =7 ;
    private StorageReference storageReference;
    private FirebaseAuth autentificacao;
    private EditText endereco, latitude, longitude, observacaoFisicas,
              observacaoAtivos,quantidadeLampada,quantidadeLampada2,quantidadeLampada3,
            potReator,potReator2,potReator3,quantidade24H,quantidade24H2,quantidade24H3 ,
                observacaoVegetacao, observacaoIP,
                quantidadeCabos, quantidadeCabos2, quantidadeCabos3, quantidadeCabos4, quantidadeCabos5,
                nome, nome2, nome3, nome4, nome5, descricaoIrregularidade, descricaoIrregularidade2,
                descricaoIrregularidade3, descricaoIrregularidade4,descricaoIrregularidade5,
                observacaoMutuo, observacaoMutuo2, observacaoMutuo3, observacaoMutuo4, observacaoMutuo5;
    private Spinner municipio,alturaCarga, tipoPoste,ipEstrutura,ipEstrutura2,ipEstrutura3,tipoPot,
            tipoPot2,tipoPot3, dimensaoVegetacao, ipAtivacao,ipAtivacao2,ipAtivacao3,
            trafoTrifasico, trafoMono,ramalSubt, quantidadeOcupantes,
            tipoCabo, tipoCabo2, tipoCabo3, tipoCabo4, tipoCabo5, finalidade, finalidade2, finalidade3,
            finalidade4, finalidade5, ceans, ceans2, ceans3, ceans4, ceans5, tar, tar2, tar3, tar4,
            tar5, reservaTec, reservaTec2, reservaTec3, reservaTec4, reservaTec5, backbone,
            backbone2, backbone3, backbone4,backbone5,distaciaBaixa, distanciaMedia,estadoArvore,
            localArvore;
    private CheckBox normal, ferragemExposta, fletido, danificado, abalrroado, trincado, religador, medicao,
            chFusivel, chFaca,vinteEQuatro,vinteEQuatro2,vinteEQuatro3,
            ativos,chkTrafoTrifasico, chkTrafoMono, ip,ip2,ip3,chFusivelReligador, chBanco, mutuo,
            placaIdentificadora, placaIdentificadora2, placaIdentificadora3, placaIdentificadora4,
            placaIdentificadora5,descidaCabos, descidaCabos2, descidaCabos3, descidaCabos4, descidaCabos5,
            quedaArvore;
    private Formulario formularioAtual;
    private Boolean controle = false;
    private TextView mutuo2, mutuo3, mutuo4, mutuo5;
    private ImageView foto, foto2, foto3;
    String imgPath, imgPath2, imgPath3;
    File photoFile = null;
    Bitmap imagem, imagem2, imagem3;
    Uri urlFoto, urlFoto2, urlFoto3;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        final View root = inflater.inflate(R.layout.fragment_cadastro, container, false);
        foto = root.findViewById(R.id.imageCadastroFoto);
        foto2 = root.findViewById(R.id.imageCadastroFoto2);
        foto3 = root.findViewById(R.id.imageCadastroFoto3);
        storageReference = ConfiguracaoFirebase.getStorageReference();
        autentificacao = ConfiguracaoFirebase.getFirebaseAuth();
        Button btnFoto = root.findViewById(R.id.btnFoto);
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

        Button btnUpload = root.findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagem == null){
                    Toast.makeText(requireActivity().getApplicationContext(), "Escolha primeiro uma foto para fazer o upload", Toast.LENGTH_SHORT).show();
                }else {
                    final AlertDialog optionDialog = new AlertDialog.Builder(requireContext(),R.style.LightDialogTheme).create();
                    optionDialog.setMessage("Por favor espere o fim do upload");
                    optionDialog.show();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                        byte[] dadosImagem = baos.toByteArray();

                        final StorageReference imageRef = storageReference
                                .child("imagens")
                                .child(Base64Custom.codificarBase64(autentificacao.getCurrentUser().getEmail()))
                                .child(endereco.getText().toString() + " " + latitude.getText().toString() + " " + longitude.getText().toString())
                                .child("foto1.jpeg");
                        UploadTask uploadTask = imageRef.putBytes(dadosImagem);
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("ERRO","EERO1");
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Log.i("ERRO","EERO2");
                                imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        urlFoto = task.getResult();
                                        optionDialog.dismiss();
                                    }
                                });
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Log.i("ERRO","EERO3");
                            }
                        });
                }
            }
        });

        Button btnUpload2 = root.findViewById(R.id.btnUpload2);
        btnUpload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagem2 == null){
                    Toast.makeText(requireActivity().getApplicationContext(), "Escolha primeiro uma foto para fazer o upload", Toast.LENGTH_SHORT).show();
                }else{
                    final AlertDialog optionDialog = new AlertDialog.Builder(requireContext(),R.style.LightDialogTheme).create();
                    optionDialog.setMessage("Por favor espere o fim do upload");
                    optionDialog.show();
                    ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                    imagem2.compress(Bitmap.CompressFormat.JPEG, 70, baos2);
                    byte[] dadosImagem2 = baos2.toByteArray();

                    final StorageReference imageRef2 = storageReference
                            .child("imagens")
                            .child(Base64Custom.codificarBase64(autentificacao.getCurrentUser().getEmail()))
                            .child(endereco.getText().toString() + " " + latitude.getText().toString() + " " + longitude.getText().toString())
                            .child("foto2.jpeg");
                    UploadTask uploadTask2 = imageRef2.putBytes(dadosImagem2);
                    uploadTask2.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(requireActivity().getApplicationContext(), "Falha ao fazer Upload", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageRef2.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    urlFoto2 = task.getResult();
                                    optionDialog.dismiss();

                                }
                            });
                        }
                    });
                }
            }
        });

        Button btnUpload3 = root.findViewById(R.id.btnUpload3);
        btnUpload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagem3 == null){
                    Toast.makeText(requireActivity().getApplicationContext(), "Escolha primeiro uma foto para fazer o upload", Toast.LENGTH_SHORT).show();
                }else{
                    final AlertDialog optionDialog = new AlertDialog.Builder(requireContext(),R.style.LightDialogTheme).create();
                    optionDialog.setMessage("Por favor espere o fim do upload");
                    optionDialog.show();
                    ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
                    imagem3.compress(Bitmap.CompressFormat.JPEG, 70, baos3);
                    byte[] dadosImagem3 = baos3.toByteArray();

                    final StorageReference imageRef3 = storageReference
                            .child("imagens")
                            .child(Base64Custom.codificarBase64(autentificacao.getCurrentUser().getEmail()))
                            .child(endereco.getText().toString() + " " + latitude.getText().toString() + " " + longitude.getText().toString())
                            .child("foto3.jpeg");
                    UploadTask uploadTask3 = imageRef3.putBytes(dadosImagem3);
                    uploadTask3.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(requireActivity().getApplicationContext(), "Falha ao fazer Upload", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageRef3.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    urlFoto3 = task.getResult();
                                    optionDialog.dismiss();

                                }
                            });
                        }
                    });
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
        abalrroado = root.findViewById(R.id.chkCadastroAbalrroado);
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

        Button buttonCadastrar = root.findViewById(R.id.btnCadastroSalvar);
        try {
            assert this.getArguments() != null;
            formularioAtual = (Formulario) this.getArguments().getSerializable("formularioSelecionado");
            if(formularioAtual != null){
                //LOCALIZAÇÃO
                controle = true;
                imgPath = formularioAtual.getCaminhoImagem();
                foto.setImageBitmap(BitmapFactory.decodeFile(imgPath));
                imgPath2 = formularioAtual.getCaminhoImagem2();
                foto2.setImageBitmap(BitmapFactory.decodeFile(imgPath2));
                imgPath3 = formularioAtual.getCaminhoImagem3();
                foto3.setImageBitmap(BitmapFactory.decodeFile(imgPath3));
                endereco.setText(formularioAtual.getEndereco());
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
                    abalrroado.setEnabled(true);
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
                if(formularioAtual.getAbalrroado().equals("Sim")){
                    abalrroado.setChecked(true);
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
                        mutuoDados(quantidadeCabos3,null,false);
                        mutuoDados(quantidadeCabos4,null,false);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,false);
                        mutuoDados(null,tipoCabo2,false);
                        mutuoDados(null,tipoCabo3,false);
                        mutuoDados(null,tipoCabo4,false);
                        mutuoDados(null,tipoCabo5,false);

                        mutuoDados(nome,null,false);
                        mutuoDados(nome2,null,false);
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
                        mutuoDados(quantidadeCabos3,null,false);
                        mutuoDados(quantidadeCabos4,null,false);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,false);
                        mutuoDados(null,tipoCabo3,false);
                        mutuoDados(null,tipoCabo4,false);
                        mutuoDados(null,tipoCabo5,false);

                        mutuoDados(nome,null,true);
                        mutuoDados(nome2,null,false);
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
                        mutuoDados(quantidadeCabos3,null,false);
                        mutuoDados(quantidadeCabos4,null,false);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,true);
                        mutuoDados(null,tipoCabo3,false);
                        mutuoDados(null,tipoCabo4,false);
                        mutuoDados(null,tipoCabo5,false);

                        mutuoDados(nome,null,true);
                        mutuoDados(nome2,null,true);
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
                        mutuoDados(quantidadeCabos3,null,true);
                        mutuoDados(quantidadeCabos4,null,false);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,true);
                        mutuoDados(null,tipoCabo3,true);
                        mutuoDados(null,tipoCabo4,false);
                        mutuoDados(null,tipoCabo5,false);

                        mutuoDados(nome,null,true);
                        mutuoDados(nome2,null,true);
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
                        mutuoDados(quantidadeCabos3,null,true);
                        mutuoDados(quantidadeCabos4,null,true);
                        mutuoDados(quantidadeCabos5,null,false);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,true);
                        mutuoDados(null,tipoCabo3,true);
                        mutuoDados(null,tipoCabo4,true);
                        mutuoDados(null,tipoCabo5,false);

                        mutuoDados(nome,null,true);
                        mutuoDados(nome2,null,true);
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
                        mutuoDados(quantidadeCabos3,null,true);
                        mutuoDados(quantidadeCabos4,null,true);
                        mutuoDados(quantidadeCabos5,null,true);

                        mutuoDados(null,tipoCabo,true);
                        mutuoDados(null,tipoCabo2,true);
                        mutuoDados(null,tipoCabo3,true);
                        mutuoDados(null,tipoCabo4,true);
                        mutuoDados(null,tipoCabo5,true);

                        mutuoDados(nome,null,true);
                        mutuoDados(nome2,null,true);
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
                        FormularioDAO formularioDAO = new FormularioDAO(requireActivity().getApplicationContext());
                        Formulario formulario = new Formulario();
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
                        if (abalrroado.isChecked()) {
                            formulario.setAbalrroado("Sim");
                        } else {
                            formulario.setAbalrroado("Não");
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
                        formulario.setData(data);
                        if (formularioDAO.salvar(formulario)) {
                            CadastradosFragment cadastradosFragment = new CadastradosFragment();

                            FragmentManager fm = getParentFragmentManager();
                            FragmentTransaction transaction = fm.beginTransaction();
                            transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                            transaction.commit();
                            Toast.makeText(requireActivity().getApplicationContext(), "Sucesso ao salvar formulário", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity().getApplicationContext(), "Erro ao salvar formulário", Toast.LENGTH_SHORT).show();
                        }
                        //endregion
                        //endregion
                    }


                });

                controle = false;

            }
        }catch (Exception e ){
         Log.e("ERRO", "erro: " + e);
        }


        normal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(normal.isChecked()){
                    ferragemExposta.setChecked(false);
                    fletido.setChecked(false);
                    danificado.setChecked(false);
                    abalrroado.setChecked(false);
                    trincado.setChecked(false);
                    ferragemExposta.setEnabled(false);
                    fletido.setEnabled(false);
                    danificado.setEnabled(false);
                    abalrroado.setEnabled(false);
                    trincado.setEnabled(false);
                }else{
                    ferragemExposta.setEnabled(true);
                    fletido.setEnabled(true);
                    danificado.setEnabled(true);
                    abalrroado.setEnabled(true);
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
                        mutuoDados(quantidadeCabos3, null, false);
                        mutuoDados(quantidadeCabos4, null, false);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, false);
                        mutuoDados(null, tipoCabo2, false);
                        mutuoDados(null, tipoCabo3, false);
                        mutuoDados(null, tipoCabo4, false);
                        mutuoDados(null, tipoCabo5, false);

                        mutuoDados(nome, null, false);
                        mutuoDados(nome2, null, false);
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



                        // endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(1).toString())) {
                        Log.i("Spinenr", "Apertei 1");
                        // region MutuoDados1
                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, false);
                        mutuoDados(quantidadeCabos3, null, false);
                        mutuoDados(quantidadeCabos4, null, false);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, false);
                        mutuoDados(null, tipoCabo3, false);
                        mutuoDados(null, tipoCabo4, false);
                        mutuoDados(null, tipoCabo5, false);

                        mutuoDados(nome, null, true);
                        mutuoDados(nome2, null, false);
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

                        //endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(2).toString())) {
                        Log.i("Spinenr", "Apertei 2");
                        //region MutuoDados2

                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, true);
                        mutuoDados(quantidadeCabos3, null, false);
                        mutuoDados(quantidadeCabos4, null, false);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, true);
                        mutuoDados(null, tipoCabo3, false);
                        mutuoDados(null, tipoCabo4, false);
                        mutuoDados(null, tipoCabo5, false);

                        mutuoDados(nome, null, true);
                        mutuoDados(nome2, null, true);
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
                        //endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(3).toString())) {
                        Log.i("Spinenr", "Apertei 3");
                        //region MutuoDados3

                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, true);
                        mutuoDados(quantidadeCabos3, null, true);
                        mutuoDados(quantidadeCabos4, null, false);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, true);
                        mutuoDados(null, tipoCabo3, true);
                        mutuoDados(null, tipoCabo4, false);
                        mutuoDados(null, tipoCabo5, false);

                        mutuoDados(nome, null, true);
                        mutuoDados(nome2, null, true);
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

                        //endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(4).toString())) {
                        Log.i("Spinenr", "Apertei 4");
                        //region MutuoDados4

                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, true);
                        mutuoDados(quantidadeCabos3, null, true);
                        mutuoDados(quantidadeCabos4, null, true);
                        mutuoDados(quantidadeCabos5, null, false);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, true);
                        mutuoDados(null, tipoCabo3, true);
                        mutuoDados(null, tipoCabo4, true);
                        mutuoDados(null, tipoCabo5, false);

                        mutuoDados(nome, null, true);
                        mutuoDados(nome2, null, true);
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

                        //endregion
                    } else if (quantidadeOcupantes.getSelectedItem().toString().equals(quantidadeOcupantes.getItemAtPosition(5).toString())) {
                        Log.i("Spinenr", "Apertei 5");
                        Log.i("Spinenr", "FUI3");
                        //region MutuoDados5

                        mutuoDados(quantidadeCabos, null, true);
                        mutuoDados(quantidadeCabos2, null, true);
                        mutuoDados(quantidadeCabos3, null, true);
                        mutuoDados(quantidadeCabos4, null, true);
                        mutuoDados(quantidadeCabos5, null, true);

                        mutuoDados(null, tipoCabo, true);
                        mutuoDados(null, tipoCabo2, true);
                        mutuoDados(null, tipoCabo3, true);
                        mutuoDados(null, tipoCabo4, true);
                        mutuoDados(null, tipoCabo5, true);

                        mutuoDados(nome, null, true);
                        mutuoDados(nome2, null, true);
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
                FormularioDAO formularioDAO = new FormularioDAO(requireActivity().getApplicationContext());
                final Formulario formulario = new Formulario();
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
                formulario.setUrlImagem(urlFoto.toString());
                formulario.setUrlImagem2(urlFoto2.toString());
                formulario.setUrlImagem3(urlFoto3.toString());

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
                if (abalrroado.isChecked()) {
                    formulario.setAbalrroado("Sim");
                } else {
                    formulario.setAbalrroado("Não");
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
                    } else {
                        Toast.makeText(requireActivity().getApplicationContext(), "Erro ao atualizar formulário", Toast.LENGTH_SHORT).show();
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
                    //endregion
                    formulario.setData(data);
                    if (formularioDAO.salvar(formulario)) {
                        CadastradosFragment cadastradosFragment = new CadastradosFragment();

                        FragmentManager fm = getParentFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                        transaction.commit();
                        Toast.makeText(requireActivity().getApplicationContext(), "Sucesso ao salvar formulário", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireActivity().getApplicationContext(), "Erro ao salvar formulário", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

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
                        imagem = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto.setImageBitmap(imagem);
                        break;
                    case IMAGE_PICK_CODE:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        foto.setImageBitmap(imagem);
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
                        foto.setImageBitmap(BitmapFactory.decodeFile(imgPath));
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
                        foto2.setImageBitmap(imagem2);
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
                        foto2.setImageBitmap(BitmapFactory.decodeFile(imgPath2));
                        break;

                    case IMAGE_CAPTURE_CODE3:
                        imgPath3 = photoFile.getAbsolutePath();
                        imagem3 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        foto3.setImageBitmap(imagem3);
                        break;
                    case IMAGE_PICK_CODE3:
                        Log.i("TAH2", data.getData().toString());
                        localImagemSelecionada = data.getData();
                        imagem3 = MediaStore.Images.Media.getBitmap(requireActivity().getApplicationContext().getContentResolver(),localImagemSelecionada);
                        foto3.setImageBitmap(imagem3);
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
                        foto3.setImageBitmap(BitmapFactory.decodeFile(imgPath3));
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
        imgPath = image.getAbsolutePath();
        return image;
    }

         /*if(cameraIntent.resolveActivity(requireActivity().getApplicationContext().getPackageManager()) != null){
             startActivityForResult(cameraIntent, imagemCodigo);
         }*/
     }