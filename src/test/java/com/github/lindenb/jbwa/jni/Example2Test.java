package com.github.lindenb.jbwa.jni;

import java.io.IOException;

/**
 * Created by xubo on 2017/3/3.
 */
public class Example2Test {
    public static void main(String[] args) {
//        String[] str = compute("pairN1");
//        String[] str = compute("testL30N1pair");
//        String[] str = compute("testL50N1pair");
        String[] str = compute("testL50N2pair");
//                {"test/GRCH38chr1L3556522.fasta", "test/pair1N1.fastq", "test/pair2N1.fastq"};
        try {
            Example2.main(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //-Djava.library.path=src/main/native
    }

    public static String[] compute(String str) {
        String[] strings = null;
        if (str == "pairN1") {
            strings = new String[]{"test/bwaindex/GRCH38chr1L3556522.fasta", "test/pair1N1.fastq", "test/pair2N1.fastq"};
        } else if (str == "testL30N1pair") {
            strings = new String[]{"test/bwaindex/GRCH38chr1L3556522.fasta", "test/fastq/testL30N1pair1.fastq", "test/fastq/testL30N1pair2.fastq"};
        } else if (str == "testL50N1pair") {
            strings = new String[]{"test/bwaindex/GRCH38chr1L3556522.fasta", "test/fastq/testL50N1pair1.fastq", "test/fastq/testL50N1pair2.fastq"};
        } else if (str == "testL50N2pair") {
            strings = new String[]{"test/bwaindex/GRCH38chr1L3556522.fasta", "test/fastq/testL50N2pair1.fastq", "test/fastq/testL50N2pair2.fastq"};
        }


        return strings;
    }
}
