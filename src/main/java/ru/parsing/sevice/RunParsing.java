package ru.parsing.sevice;

import ru.parsing.SourceData;
import ru.parsing.files.beton.Paritet;
import ru.parsing.files.brick.*;
import ru.parsing.files.cable.CableRu;
import ru.parsing.files.cable.Ekc;
import ru.parsing.files.cable.Etm;
import ru.parsing.files.cable.GlavSnabCable;

import ru.parsing.files.docExcel.SourceExcel;
import ru.parsing.files.heater.GlavSnabHeater;
import ru.parsing.files.heater.Neotreid;
import ru.parsing.files.heater.StParHeater;
import ru.parsing.files.heater.TskdiplomatHeater;
import ru.parsing.files.limestone.MgStone;
import ru.parsing.files.membrane.GlavSnabWaterProof;
import ru.parsing.files.membrane.Tskdiplomat;
import ru.parsing.files.paving.*;
import ru.parsing.files.pipes.HidroControl;
import ru.parsing.files.pipes.KermiMarket;
import ru.parsing.files.pipes.Teremonline;
import ru.parsing.files.roofIzol.*;
import ru.parsing.files.stoneware.GvavSnabStoneWare;

import java.io.IOException;
import java.util.List;

public class RunParsing {
    public static void main(String[] args) throws IOException {
        //DocExcel

        SourceExcel sourceExcel = new SourceExcel();
        Parsing parsing = new Parsing();
        List<SourceData> finalList = sourceExcel.parsData();
        parsing.pars(finalList);

        //Beton

        Paritet paritet = new Paritet();
        List<SourceData> listParitet = paritet.parsData();
        parsing.pars(listParitet);

        //Cable

        CableRu cableRu = new CableRu();
        List<SourceData> listCableRu = cableRu.parsData();
        parsing.pars(listCableRu);

        Etm etm = new Etm();
        List<SourceData> listEtm = etm.parsData();
        parsing.pars(listEtm);


        Ekc ekc = new Ekc();
        List<SourceData> listEkc = ekc.parsData();
        parsing.pars(listEkc);

        GlavSnabCable glavSnabCable = new GlavSnabCable();
        List<SourceData> listGvavSnabCable = glavSnabCable.parsData();
        parsing.pars(listGvavSnabCable);

        //Brick

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

        StPar stPar= new StPar();
        List<SourceData> listStPar= stPar.parsData();
        parsing.pars(listStPar);


        //Whaterproofing

        GlavSnabWaterProof glavSnabWaterProof = new GlavSnabWaterProof();
        List<SourceData> listGvavSnabWaterProof= glavSnabWaterProof.parsData();
        parsing.pars(listGvavSnabWaterProof);

        Tskdiplomat tskdiplomat= new Tskdiplomat();
        List<SourceData> listTskdiplomat= tskdiplomat.parsData();
        parsing.pars(listTskdiplomat);
        //paving

        SlavDom slavDom= new SlavDom();
        List<SourceData> listSlavdom= slavDom.parsData();
        parsing.pars(listSlavdom);

        KlinkerHouse klinkerHouse= new KlinkerHouse();
        List<SourceData> listKlinkerHouse= klinkerHouse.parsData();
        parsing.pars(listKlinkerHouse);

        GvavSnabPaving gvavSnabPaving= new GvavSnabPaving();
        List<SourceData> listGvavSnabPaving= gvavSnabPaving.parsData();
        parsing.pars(listGvavSnabPaving);

        TheBrick theBrick= new TheBrick();
        List<SourceData> listTheBrick= theBrick.parsData();
        parsing.pars(listTheBrick);


        PetrovichPaving petrovichPaving= new PetrovichPaving();
        List<SourceData> listPetrovichPaving= petrovichPaving.parsData();
        parsing.pars(listPetrovichPaving);

        Braer braer= new Braer();
        List<SourceData> listBraer= braer.parsData();
        parsing.pars(listBraer);


        //Pipes

        HidroControl hidroControl= new HidroControl();
        List<SourceData> listHidroControl= hidroControl.parsData();
        parsing.pars(listHidroControl);

        KermiMarket kermiMarket= new KermiMarket();
        List<SourceData> listKermiMarket= kermiMarket.parsData();
        parsing.pars(listKermiMarket);

        Teremonline teremonline= new Teremonline();
        List<SourceData> listTeremonline= teremonline.parsData();
        parsing.pars(listTeremonline);

        //Roof

        GlavSnabRoof glavSnabRoof= new GlavSnabRoof();
        List<SourceData> listGlavSnabRoof= glavSnabRoof.parsData();
        parsing.pars(listGlavSnabRoof);

        ShopTn shopTn= new ShopTn();
        List<SourceData> listShopTn= shopTn.parsData();
        parsing.pars(listShopTn);

        TnMSK tnMSK= new TnMSK();
        List<SourceData> listTnMSK= tnMSK.parsData();
        parsing.pars(listTnMSK);

        TsKrovisol tsKrovisol= new TsKrovisol();
        List<SourceData> listTsKrovisol =tsKrovisol.parsData();
        parsing.pars(listTsKrovisol);

        Tstn tstn= new Tstn();
        List<SourceData> listTstn =tstn.parsData();
        parsing.pars(listTstn);


        //LimeStone

        MgStone mgStone= new MgStone();
        List<SourceData> listMgStone =mgStone.parsData();
        parsing.pars(listMgStone);

         //StoneWare

        GvavSnabStoneWare gvavSnabStoneWare  = new GvavSnabStoneWare();
        List<SourceData> listGvavSnabStoneWare =gvavSnabStoneWare.parsData();
        parsing.pars(listGvavSnabStoneWare);

        //heater

        Neotreid neotreid = new Neotreid();
        List<SourceData> listNeotreid =neotreid.parsData();
        parsing.pars(listNeotreid);

        GlavSnabHeater glavSnabHeater = new GlavSnabHeater();
        List<SourceData> listGlavSnabHeater =glavSnabHeater.parsData();
        parsing.pars(listGlavSnabHeater);

        StParHeater stParHeater = new StParHeater();
        List<SourceData> listStParHeater =stParHeater.parsData();
        parsing.pars(listStParHeater);

        TskdiplomatHeater tskdiplomatHeater = new TskdiplomatHeater();
        List<SourceData> listSTskdiplomatHeater =tskdiplomatHeater.parsData();
        parsing.pars(listSTskdiplomatHeater);

    }
}
