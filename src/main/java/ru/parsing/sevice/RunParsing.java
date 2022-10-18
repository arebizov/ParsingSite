package ru.parsing.sevice;

import ru.parsing.SourceData;
import ru.parsing.files.SourceExcel;

import java.io.IOException;
import java.util.List;

public class RunParsing {
    public static void main(String[] args) throws IOException {
        SourceExcel sourceExcel = new SourceExcel();
        List<SourceData> finalList = sourceExcel.ParsExcel();

        Parsing parsing = new Parsing();
        parsing.pars(finalList);


    }
}
