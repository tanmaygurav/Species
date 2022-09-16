package com.ruia.species;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.Callable;

public class AR extends AppCompatActivity {
    private ArFragment arCam; //object of ArFragment Class

    private int clickNo = 0; //helps to render the 3d model only once when we tap the screen
    String ProjectName=null,ScientificName=null;
    TextView commonName, SciName;
    ProgressDialog loading;

    private StorageReference mStorageRef;
    File localFile;



    public static boolean checkSystemSupport(Activity activity) {

        //checking whether the API version of the running Android >= 24 that means Android Nougat 7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();

            //checking whether the OpenGL version >= 3.0
            if (Double.parseDouble(openGlVersion) >= 3.0) {
                return true;
            } else {
                Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_SHORT).show();
                activity.finish();
                return false;
            }
        } else {
            Toast.makeText(activity, "App does not support required Build Version", Toast.LENGTH_SHORT).show();
            activity.finish();
            return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        FirebaseApp.initializeApp(getApplicationContext());

        commonName=findViewById(R.id.idSpecimenCommonName);
        SciName=findViewById(R.id.idSpecimenSciName);



        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
            try{
//            ProjectName = extras.getString("projectName");
            ScientificName= extras.getString("SciName");
            }finally {
                ProjectName = extras.getString("Common Name");
                ScientificName= extras.getString("Sci Name");
            }


        commonName.setText(ProjectName);
        SciName.setText(ScientificName);


        getModel();
        
        /*switch (ProjectName){
            case "Monitor Lizard":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.monitorlizard)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Rabbit":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.rabbit)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Bee":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.bee)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Jellyfish":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.jellyfish)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Komodo Dragon":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.komododragon)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Ulysses Butterfly":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.ulyssesbutterfly)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Octopus":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.ocutopus)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "SeaHorse":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.seahorse)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
            case "Demo":
                if (checkSystemSupport(this)) {
                    arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
                    //ArFragment is linked up with its respective id used in the activity_main.xml
                    arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        clickNo++;
                        //the 3d model comes to the scene only when clickNo is one that means once
                        if (clickNo == 1) {
                            Anchor anchor = hitResult.createAnchor();
                            ModelRenderable.builder()
                                    .setSource(this, R.raw.gfg_gold_text_stand_2)
                                    .setIsFilamentGltf(true)
                                    .build()
                                    .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                                    .exceptionally(throwable -> {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                        return null;
                                    });
                        }
                    });
                } else {
                    return;
                }
                break;
        }*/




    }
    private void getModel() {
        String filepath=ScientificName+".glb";
        try {
            mStorageRef = FirebaseStorage.getInstance().getReference().child(filepath+".glb");
        }catch (Exception e){
            e.printStackTrace();
        }


        loading = ProgressDialog.show(this,"Loading","Please Wait",false,true);

        localFile = null;
        try {
            localFile = File.createTempFile("Model", "glb");

        mStorageRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        if (loading.isShowing()) {
                            loading.dismiss();
                        }
//                        show model code
                        displayAR();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle failed download
                        // ...
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayAR() {
        if (checkSystemSupport(this)) {
            arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
            //ArFragment is linked up with its respective id used in the activity_main.xml
            arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                clickNo++;
                //the 3d model comes to the scene only when clickNo is one that means once
                if (clickNo == 1) {
                    Anchor anchor = hitResult.createAnchor();
                    ModelRenderable.builder()
                            .setSource(this, (Callable<InputStream>) localFile)
                            .setIsFilamentGltf(true)
                            .build()
                            .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                            .exceptionally(throwable -> {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Something is not right" + throwable.getMessage()).show();
                                return null;
                            });
                }
            });
        } else {
            return;
        }
    }

    private void addModel(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        // Creating a AnchorNode with a specific anchor
        anchorNode.setParent(arCam.getArSceneView().getScene());
        //attaching the anchorNode with the ArFragment
        TransformableNode model = new TransformableNode(arCam.getTransformationSystem());
        model.setParent(anchorNode);
        //attaching the anchorNode with the TransformableNode
        model.setRenderable(modelRenderable);
        //attaching the 3d model with the TransformableNode that is already attached with the node
        model.select();
    }
}