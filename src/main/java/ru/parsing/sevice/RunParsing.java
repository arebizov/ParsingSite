package ru.parsing.sevice;

import ru.parsing.SourceData;

import ru.parsing.files.asphalt.*;
import ru.parsing.files.beton.Paritet;
import ru.parsing.files.brick.*;
import ru.parsing.files.cable.*;
import ru.parsing.files.cement.*;
import ru.parsing.files.crushedStone.*;
import ru.parsing.files.docExcel.SourceExcel;
import ru.parsing.files.drywall.*;
import ru.parsing.files.duct.*;
import ru.parsing.files.haydite.*;
import ru.parsing.files.heater.*;
import ru.parsing.files.limestone.MgStone;
import ru.parsing.files.membrane.*;
import ru.parsing.files.metallRolling.*;
import ru.parsing.files.paving.*;
import ru.parsing.files.pipes.*;
import ru.parsing.files.plaster.*;
import ru.parsing.files.roofIzol.*;
import ru.parsing.files.sand.*;
import ru.parsing.files.stone.*;
import ru.parsing.files.stoneware.*;


import java.io.IOException;
import java.util.List;

public class RunParsing {
    public static void main(String[] args) throws IOException {
        //DocExcel

        SourceExcel sourceExcel = new SourceExcel();
        Parsing parsing = new Parsing();
        List<SourceData> finalList = sourceExcel.parsData();
        parsing.pars(finalList);

//        //Beton

        Paritet paritet = new Paritet();
        List<SourceData> listParitet = paritet.parsData();
        parsing.pars(listParitet);

////        //Cable

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
//////
//////        //Brick
////
        Ccomplekt ccomplekt= new Ccomplekt();
        List<SourceData> listCcomlect= ccomplekt.parsData();
        parsing.pars(listCcomlect);

        Kirpich kirpich= new Kirpich();
        List<SourceData> listKirpich= kirpich.parsData();
        parsing.pars(listKirpich);

        MosCeram mosCeram= new MosCeram();
        List<SourceData> listMosCeram= mosCeram.parsData();
        parsing.pars(listMosCeram);
//
        OblCeram oblCeram= new OblCeram();
        List<SourceData> listOblCeram= oblCeram.parsData();
        parsing.pars(listOblCeram);

        RuKeram ruKeram = new RuKeram();
        List<SourceData> listRuKeram = ruKeram.parsData();
        parsing.pars(listRuKeram);

        SDvor sDvor= new SDvor();
        List<SourceData> listSDvor= sDvor.parsData();
        parsing.pars(listSDvor);

        StPar stPar= new StPar();
        List<SourceData> listStPar= stPar.parsData();
        parsing.pars(listStPar);

////////        //Membane

        GlavSnabWaterProof glavSnabWaterProof = new GlavSnabWaterProof();
        List<SourceData> listGvavSnabWaterProof= glavSnabWaterProof.parsData();
        parsing.pars(listGvavSnabWaterProof);

        Tskdiplomat tskdiplomat= new Tskdiplomat();
        List<SourceData> listTskdiplomat= tskdiplomat.parsData();
        parsing.pars(listTskdiplomat);

////////        paving

        Braer braer= new Braer();
        List<SourceData> listBraer= braer.parsData();
        parsing.pars(listBraer);

        GvavSnabPaving gvavSnabPaving= new GvavSnabPaving();
        List<SourceData> listGvavSnabPaving= gvavSnabPaving.parsData();
        parsing.pars(listGvavSnabPaving);

        KlinkerHouse klinkerHouse= new KlinkerHouse();
        List<SourceData> listKlinkerHouse= klinkerHouse.parsData();
        parsing.pars(listKlinkerHouse);

        PetrovichPaving petrovichPaving= new PetrovichPaving();
        List<SourceData> listPetrovichPaving= petrovichPaving.parsData();
        parsing.pars(listPetrovichPaving);

        SlavDom slavDom= new SlavDom();
        List<SourceData> listSlavdom= slavDom.parsData();
        parsing.pars(listSlavdom);

        TheBrick theBrick= new TheBrick();
        List<SourceData> listTheBrick= theBrick.parsData();
        parsing.pars(listTheBrick);

//////
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
//
        McenaPipesZinc mcenaPipesZinc = new McenaPipesZinc();
        List<SourceData> listMcenaPipesZinc = mcenaPipesZinc.parsData();
        parsing.pars(listMcenaPipesZinc);
//
////////        //Roofizol
//
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

////////        //LimeStone

        MgStone mgStone= new MgStone();
        List<SourceData> listMgStone =mgStone.parsData();
        parsing.pars(listMgStone);

////////         //StoneWare

        EstimaStoneWare estimaStoneWare  = new EstimaStoneWare();
        List<SourceData> listEstimaStoneWare =estimaStoneWare.parsData();
        parsing.pars(listEstimaStoneWare);

        GvavSnabStoneWare gvavSnabStoneWare  = new GvavSnabStoneWare();
        List<SourceData> listGvavSnabStoneWare =gvavSnabStoneWare.parsData();
        parsing.pars(listGvavSnabStoneWare);

////////        //heater

        BikraM bikraM = new BikraM();
        List<SourceData> listSBikraM =bikraM.parsData();
        parsing.pars(listSBikraM);

        GlavSnabHeater glavSnabHeater = new GlavSnabHeater();
        List<SourceData> listGlavSnabHeater =glavSnabHeater.parsData();
        parsing.pars(listGlavSnabHeater);

        Neotreid neotreid = new Neotreid();
        List<SourceData> listNeotreid =neotreid.parsData();
        parsing.pars(listNeotreid);

        StParHeater stParHeater = new StParHeater();
        List<SourceData> listStParHeater =stParHeater.parsData();
        parsing.pars(listStParHeater);

        Tmos tmos = new Tmos();
        List<SourceData> listTmos =tmos.parsData();
        parsing.pars(listTmos);

        TskdiplomatHeater tskdiplomatHeater = new TskdiplomatHeater();
        List<SourceData> listSTskdiplomatHeater =tskdiplomatHeater.parsData();
        parsing.pars(listSTskdiplomatHeater);

        VentoRus ventoRus = new VentoRus();
        List<SourceData> listVentoRus =ventoRus.parsData();
        parsing.pars(listVentoRus);


//////        //Asphalt

        Abscds abscds = new Abscds();
        List<SourceData> listAbscds =abscds.parsData();
        parsing.pars(listAbscds);

        AbzAsphalt abzAsphalt = new AbzAsphalt();
        List<SourceData> listAbzAsphalt =abzAsphalt.parsData();
        parsing.pars(listAbzAsphalt);

        ABZRu abzRu = new ABZRu();
        List<SourceData> listABZRu =abzRu.parsData();
        parsing.pars(listABZRu);

        IAsfalt iAsfalt = new IAsfalt();
        List<SourceData> listIAsfalt =iAsfalt.parsData();
        parsing.pars(listIAsfalt);

        McenaAsphalt mcenaAsphalt = new McenaAsphalt();
        List<SourceData> listMcenaAsphalt= mcenaAsphalt.parsData();
        parsing.pars(listMcenaAsphalt);

        PlatonStroy platonStroy = new PlatonStroy();
        List<SourceData> listPlatonStroy =platonStroy.parsData();
        parsing.pars(listPlatonStroy);


//
////////        // cement

        PetrovichCement petrovichCement = new PetrovichCement();
        List<SourceData> listPetrovichCement =petrovichCement.parsData();
        parsing.pars(listPetrovichCement);

        SaturnCement saturnCement = new SaturnCement();
        List<SourceData> listSaturnCement =saturnCement.parsData();
        parsing.pars(listSaturnCement);

////////        //crushedStone

        BetonTransStroy betonTransStroy = new BetonTransStroy();
        List<SourceData> listBetonTransStroy =betonTransStroy.parsData();
        parsing.pars(listBetonTransStroy);

        EcoCrushedStone ecoCrushedStone = new EcoCrushedStone();
        List<SourceData> listEcoCrushedStone =ecoCrushedStone.parsData();
        parsing.pars(listEcoCrushedStone);

//////        //duct

        CityMetall cityMetall = new CityMetall();
        List<SourceData> listCityMetall =cityMetall.parsData();
        parsing.pars(listCityMetall);

        GosMetall gosMetall = new GosMetall();
        List<SourceData> listGosMetall =gosMetall.parsData();
        parsing.pars(listGosMetall);


        McenaDuct mcenaDuct = new McenaDuct();
        List<SourceData> listMcenaDuct = mcenaDuct.parsData();
        parsing.pars(listMcenaDuct);


        RosMet rosMet = new RosMet();
        List<SourceData> listRosMet =rosMet.parsData();
        parsing.pars(listRosMet);

        ShopMetall shopMetall = new ShopMetall();
        List<SourceData> listShopMetall =shopMetall.parsData();
        parsing.pars(listShopMetall);

        VentComplect ventComplect = new VentComplect();
        List<SourceData> listVentComplect =ventComplect.parsData();
        parsing.pars(listVentComplect);

        VentSystem ventSystem = new VentSystem();
        List<SourceData> listVentSystem =ventSystem.parsData();
        parsing.pars(listVentSystem);


//
//
////////        //haydite
//
        CcomplektHaydite ccomplektHaydite = new CcomplektHaydite();
        List<SourceData> listCcomplektHaydite =ccomplektHaydite.parsData();
        parsing.pars(listCcomplektHaydite);

        McenaHaydite mcenaHaydite = new McenaHaydite();
        List<SourceData> listMcenaHaydite = mcenaHaydite.parsData();
        parsing.pars(listMcenaHaydite);
//
        NerudSnabHaydite nerudSnabHaydite = new NerudSnabHaydite();
        List<SourceData> listNerudSnabHaydite =nerudSnabHaydite.parsData();
        parsing.pars(listNerudSnabHaydite);

        PetrovichHaydite petrovichHaydite = new PetrovichHaydite();
        List<SourceData> listPetrovichHaydite =petrovichHaydite.parsData();
        parsing.pars(listPetrovichHaydite);

        TgStroy tgStroy = new TgStroy();
        List<SourceData> listTgStroy =tgStroy.parsData();
        parsing.pars(listTgStroy);

        VektorE vektorE = new VektorE();
        List<SourceData> listVektorE =vektorE.parsData();
        parsing.pars(listVektorE);

//////        //sand

        HoumStroi houmStroi = new HoumStroi();
        List<SourceData> listHoumStroi =houmStroi.parsData();
        parsing.pars(listHoumStroi);

        NerudSnab nerudSnab = new NerudSnab();
        List<SourceData> listNerudSnab =nerudSnab.parsData();
        parsing.pars(listNerudSnab);

        RoviService roviService = new RoviService();
        List<SourceData> listRoviService =roviService.parsData();
        parsing.pars(listRoviService);
//
        TdStroy tdStroy = new TdStroy();
        List<SourceData> listTdStroy = tdStroy.parsData();
        parsing.pars(listTdStroy);


        McenaSand mcenaSand = new McenaSand();
        List<SourceData> listMcenaSand = mcenaSand.parsData();
        parsing.pars(listMcenaSand);
//
//////        //stone

        Alkasar alkasar = new Alkasar();
        List<SourceData> listAlkasar =alkasar.parsData();
        parsing.pars(listAlkasar);

        GranitPk granitPk = new GranitPk();
        List<SourceData> listGranitPk =granitPk.parsData();
        parsing.pars(listGranitPk);

        KamenHouse kamenHouse = new KamenHouse();
        List<SourceData> listKamenHouse =kamenHouse.parsData();
        parsing.pars(listKamenHouse);

        KamVek kamVek = new KamVek();
        List<SourceData> listKamVek =kamVek.parsData();
        parsing.pars(listKamVek);

        PalmiraStone palmiraStone = new PalmiraStone();
        List<SourceData> listPalmiraStone =palmiraStone.parsData();
        parsing.pars(listPalmiraStone);

//////        //plaster

        KupiKnauf kupiKnauf = new KupiKnauf();
        List<SourceData> listKupiKnauf =kupiKnauf.parsData();
        parsing.pars(listKupiKnauf);

        PetrovichPlaster petrovichPlaster = new PetrovichPlaster();
        List<SourceData> listPetrovichPlaster =petrovichPlaster.parsData();
        parsing.pars(listPetrovichPlaster);

//        // Drywall

        KupiKnaufDrywall kupiKnaufDrywall = new KupiKnaufDrywall();
        List<SourceData> listKupiKnaufDrywall =kupiKnaufDrywall.parsData();
        parsing.pars(listKupiKnaufDrywall);

        PetrovichDrywall petrovichDrywall = new PetrovichDrywall();
        List<SourceData> listPetrovichDrywall =petrovichDrywall.parsData();
        parsing.pars(listPetrovichDrywall);

        RdStroyDrywall rdStroyDrywall = new RdStroyDrywall();
        List<SourceData> listRdStroyDrywall =rdStroyDrywall.parsData();
        parsing.pars(listRdStroyDrywall);

        SDvorDryWall sDvorDryWall = new SDvorDryWall();
        List<SourceData> listSDvorDryWall =sDvorDryWall.parsData();
        parsing.pars(listSDvorDryWall);

        //metallRolling

        McenaBeam mcenaBeam = new McenaBeam();
        List<SourceData> listMcenaBeam = mcenaBeam.parsData();
        parsing.pars(listMcenaBeam);

        McenaIronStick mcenaIronStick = new McenaIronStick();
        List<SourceData> listMcenaIronStick = mcenaIronStick.parsData();
        parsing.pars(listMcenaIronStick);

        McenaList mcenaList = new McenaList();
        List<SourceData> listMcenaList = mcenaList.parsData();
        parsing.pars(listMcenaList);

        McenaMetallStripe mcenaMetallStripe = new McenaMetallStripe();
        List<SourceData> listMcenaMetallStripe = mcenaMetallStripe.parsData();
        parsing.pars(listMcenaMetallStripe);

        McenaPipesSquareSteel mcenaPipesSquareSteel = new McenaPipesSquareSteel();
        List<SourceData> listMcenaPipesSquareSteel = mcenaPipesSquareSteel.parsData();
        parsing.pars(listMcenaPipesSquareSteel);

        McenaChannel mcenaChannel = new McenaChannel();
        List<SourceData> listMcenaChannel = mcenaChannel.parsData();
        parsing.pars(listMcenaChannel);

        McenaCorner mcenaCorner = new McenaCorner();
        List<SourceData> listMcenaMcenaCorner = mcenaCorner.parsData();
        parsing.pars(listMcenaMcenaCorner);

        McenaCornerv2 mcenaCornerv2 = new McenaCornerv2();
        List<SourceData> listMcenaCornerv2 = mcenaCornerv2.parsData();
        parsing.pars(listMcenaCornerv2);





    }
}
