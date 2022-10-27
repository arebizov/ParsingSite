package ru.parsing.sevice;

import ru.parsing.SourceData;
import ru.parsing.files.beton.Paritet;
import ru.parsing.files.brick.*;
import ru.parsing.files.cable.CableRu;
import ru.parsing.files.cable.Etm;
import ru.parsing.files.docExcel.SourceExcel;
import ru.parsing.files.paving.*;
import ru.parsing.files.waterProofing.GvavSnabWaterProof;
import ru.parsing.files.waterProofing.Tskdiplomat;

import java.io.IOException;
import java.util.List;

public class RunParsing {
    public static void main(String[] args) throws IOException {
        SourceExcel sourceExcel = new SourceExcel();


        Parsing parsing = new Parsing();
//        List<SourceData> finalList = sourceExcel.parsData();
//        parsing.pars(finalList);
//
//        Paritet paritet = new Paritet();
//        List<SourceData> listParitet = paritet.parsData();
//        parsing.pars(listParitet);
//
//        CableRu cableRu = new CableRu();
//        List<SourceData> listCableRu = cableRu.parsData();
//        parsing.pars(listCableRu);
//
//        Etm etm = new Etm();
//        List<SourceData> listEtm = etm.parsData();
//        parsing.pars(listEtm);
//
//        RuKeram ruKeram = new RuKeram();
//        List<SourceData> listRuKeram = ruKeram.parsData();
//        parsing.pars(listRuKeram);
//
//        OblCeram oblCeram= new OblCeram();
//        List<SourceData> listOblCeram= oblCeram.parsData();
//        parsing.pars(listOblCeram);
//
//
//        Kirpich kirpich= new Kirpich();
//        List<SourceData> listKirpich= kirpich.parsData();
//        parsing.pars(listKirpich);
//
//        MosCeram mosCeram= new MosCeram();
//        List<SourceData> listMosCeram= mosCeram.parsData();
//        parsing.pars(listMosCeram);
//
//        SDvor sDvor= new SDvor();
//        List<SourceData> listSDvor= sDvor.parsData();
//        parsing.pars(listSDvor);
//
//        Ccomplekt ccomplekt= new Ccomplekt();
//        List<SourceData> listCcomlect= ccomplekt.parsData();
//        parsing.pars(listCcomlect);
//
//        GvavSnabWaterProof gvavSnabWaterProof= new GvavSnabWaterProof();
//        List<SourceData> listGvavSnabWaterProof= gvavSnabWaterProof.parsData();
//        parsing.pars(listGvavSnabWaterProof);


//        Braer braer= new Braer();
//        List<SourceData> listBraer= braer.parsData();
//        parsing.pars(listBraer);
//
//
//        GvavSnabPaving gvavSnabPaving= new GvavSnabPaving();
//        List<SourceData> listGvavSnabPaving= gvavSnabPaving.parsData();
//        parsing.pars(listGvavSnabPaving);
//
//        TheBrick theBrick= new TheBrick();
//        List<SourceData> listTheBrick= theBrick.parsData();
//        parsing.pars(listTheBrick);
//
//
//        PetrovichPaving petrovichPaving= new PetrovichPaving();
//        List<SourceData> listPetrovichPaving= petrovichPaving.parsData();
//        parsing.pars(listPetrovichPaving);
//
//
//        SlavDom slavDom= new SlavDom();
//        List<SourceData> listSlavdom= slavDom.parsData();
//        parsing.pars(listSlavdom);


        KlinkerHouse klinkerHouse= new KlinkerHouse();
        List<SourceData> listKlinkerHouse= klinkerHouse.parsData();
        parsing.pars(listKlinkerHouse);

        Tskdiplomat tskdiplomat= new Tskdiplomat();
        List<SourceData> listTskdiplomat= tskdiplomat.parsData();
        parsing.pars(listTskdiplomat);

    }
}
