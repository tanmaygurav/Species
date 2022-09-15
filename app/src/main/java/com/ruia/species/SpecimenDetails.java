package com.ruia.species;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SpecimenDetails extends AppCompatActivity {
    TextView commonName, sciName, kingdom, subKingdom, infraKingdom, grade, division,
            subDivision, phylum, group, subPhylum, superClass, class1, subClass,
            infraClass, superOrder, order, subOrder, infraOrder, family, genus, description, ref;
    Button audio, extLinks, view3D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specimen_details);

        commonName=findViewById(R.id.idSpecimenCommonName);
        sciName=findViewById(R.id.idSpecimenSciName);
        kingdom=findViewById(R.id.idKingdom);
        subKingdom=findViewById(R.id.idSubKingdom);
        infraKingdom=findViewById(R.id.idInfraKingdom);
        grade=findViewById(R.id.idGrade);
        division=findViewById(R.id.idDivision);
        subDivision=findViewById(R.id.idSubDivision);
        phylum=findViewById(R.id.idPhylum);
        group=findViewById(R.id.idGroup);
        subPhylum=findViewById(R.id.idSubPhylum);
        superClass=findViewById(R.id.idSuperClass);
        class1=findViewById(R.id.idClass);
        subClass=findViewById(R.id.idSubClass);
        infraClass=findViewById(R.id.idInfraClass);
        superOrder=findViewById(R.id.idSuperOrder);
        order=findViewById(R.id.idOrder);
        subOrder=findViewById(R.id.idSubOrder);
        infraOrder=findViewById(R.id.idInfraOrder);
        family=findViewById(R.id.idFamily);
        genus=findViewById(R.id.idGenus);
        description=findViewById(R.id.idDescription);
        ref=findViewById(R.id.idReferences);

        audio=findViewById(R.id.idAudio);
        extLinks=findViewById(R.id.idExternalLinks);
        view3D=findViewById(R.id.idView3DModel);

    }
}