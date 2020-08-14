package datastructure.advanced;


/**
* Title: 位图
* Desc: 
* Created by Myth-MBP on 14/08/2020 in VSCode
*/

public class BitSet {
    // 使用long数组存储位信息
    long[] words;
    int ADDRESS_BITS_PER_WORD = 6;  // 2^6 == 64(long到长度)
    
    public BitSet(int nbits) {  
        words = new long[wordIndex(nbits-1) + 1];
    }  
    
    int wordIndex(int bitIndex) {  
        return bitIndex >> ADDRESS_BITS_PER_WORD;  
    }

    // public void set(int bitIndex) {  
    //     int wordIndex = wordIndex(bitIndex);  
    //     expandTo(wordIndex);  
    //     words[wordIndex] |= (1L << bitIndex); // 1L最多64位，bitIndex超过这个大小，会进行循环移位，所以不用取模 
    // }  

        // private void expandTo(int wordIndex) {  
        //     int wordsRequired = wordIndex+1;  
        //     if (wordsInUse < wordsRequired) {  
        //         ensureCapacity(wordsRequired);  
        //         wordsInUse = wordsRequired;  
        //     }  
        //     }  
        //     private void ensureCapacity(int wordsRequired) {  
        //         if (words.length < wordsRequired) {  
        //             // Allocate larger of doubled size or required size  
        //             int request = Math.max(2 * words.length, wordsRequired);  
        //                 words = Arrays.copyOf(words, request);  
        //                 sizeIsSticky = false;  
        //             }  
        //         } 

        //         public boolean get(int bitIndex) {  
        //             if (bitIndex < 0)  
        //                 throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);  
                   
        //             checkInvariants();  
                   
        //             int wordIndex = wordIndex(bitIndex);  
        //             return (wordIndex < wordsInUse)  
        //                 && ((words[wordIndex] & (1L << bitIndex)) != 0);  
        //             } 
}