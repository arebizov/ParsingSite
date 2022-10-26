package ru.parsing.sevice;

import ru.parsing.SourceData;
import ru.parsing.files.beton.Paritet;
import ru.parsing.files.brick.*;
import ru.parsing.files.cable.CableRu;
import ru.parsing.files.cable.Etm;
import ru.parsing.files.doc.SourceExcel;

import java.io.IOException;
import java.util.List;

public class RunParsing {
    public static void main(String[] args) throws IOException {
        SourceExcel sourceExcel = new SourceExcel();


        Parsing parsing = new Parsing();
        List<SourceData> finalList = sourceExcel.parsData();
        parsing.pars(finalList);

        Paritet paritet = new Paritet();
        List<SourceData> listParitet = paritet.parsData();
        parsing.pars(listParitet);

        CableRu cableRu = new CableRu();
        List<SourceData> listCableRu = cableRu.parsData();
        parsing.pars(listCableRu);

        Etm etm = new Etm();
        List<SourceData> listEtm = etm.parsData();
        parsing.pars(listEtm);

        RuKeram ruKeram = new RuKeram();
        List<SourceData> listRuKeram = ruKeram.parsData();
        parsing.pars(listRuKeram);

        OblCeram oblCeram= new OblCeram();
        List<SourceData> listOblCeram= oblCeram.parsData();
        parsing.pars(listOblCeram);


        Kirpich kirpich= new Kirpich();
        List<SourceData> listKirpich= kirpich.parsData();
        parsing.pars(listKirpich);

        MosCeram mosCeram= new MosCeram();
        List<SourceData> listMosCeram= mosCeram.parsData();
        parsing.pars(listMosCeram);

        SDvor sDvor= new SDvor();
        List<SourceData> listSDvor= sDvor.parsData();
        parsing.pars(listSDvor);


        Ccomplekt ccomplekt= new Ccomplekt();
        List<SourceData> listCcomlect= ccomplekt.parsData();
        parsing.pars(listCcomlect);


    }
}
