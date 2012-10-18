package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/20/12
 * Time: 4:28 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class FastMultiplication {

    /**
     * Toom-Cook algorithm is the way to solve this. Right now I am using BigInteger which internally uses this for large
     * number multiplication. I will upload my version soon.
     */

    public static void main(String [] args)
    {
        try
        {

            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            int numberOfTestCases = Integer.parseInt(consoleReader.readLine());
            StringBuilder out = new StringBuilder();
            for(int i=0; i<numberOfTestCases; i++)
            {
                  String [] input = consoleReader.readLine().split(" ");
                  BigInteger first = new BigInteger(input[0]);
                  BigInteger second = new BigInteger(input[1]);
                  out.append(mult(first, second)).append("\n");
            }

            System.out.println();
            System.out.println(out);
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private static BigInteger mult(BigInteger a, BigInteger b) {
        // remove any minus signs, multiply, then fix sign
        int signum = a.signum() * b.signum();
        if (a.signum() < 0)
            a = a.negate();
        if (b.signum() < 0)
            b = b.negate();

        int[] aIntArr = toIntArray(a);
        int[] bIntArr = toIntArray(b);

        int[] cIntArr = mult(aIntArr, a.bitLength(), bIntArr, b.bitLength());

        BigInteger c = toBigInteger(cIntArr);
        if (signum < 0)
            c = c.negate();

        return c;
    }

    private static int getDftExponent(int n, int v, int idx, boolean even) {
        // take bits n-v..n-1 of idx, reverse them, shift left by n-v-1
        int x = Integer.reverse(idx) << (n-v) >>> (31-n);

        // if m is even, divide by two
        if (even)
            x >>>= 1;

        return x;
    }

    private static int getIdftExponent(int n, int v, int idx, boolean even) {
        int x = Integer.reverse(idx) << (n-v) >>> (32-n);
        x += even ? 1<<(n-v) : 1<<(n-1-v);
        return x + 1;
    }

    static void addModFn(int[] a, int[] b) {
        boolean carry = false;
        for (int i=0; i<a.length; i++) {
            int sum = a[i] + b[i];
            if (carry)
                sum++;
            carry = ((sum>>>31) < (a[i]>>>31)+(b[i]>>>31));   // carry if signBit(sum) < signBit(a)+signBit(b)
            a[i] = sum;
        }

        // take a mod Fn by adding any remaining carry bit to the lowest bit;
        // since Fn ≡ 1 (mod 2^n), it suffices to add 1
        int i = 0;
        while (carry) {
            int sum = a[i] + 1;
            a[i] = sum;
            carry = sum == 0;
            i++;
            if (i >= a.length)
                i = 0;
        }
    }

    static int[] multModFn(int[] a, int[] b) {
        int[] a0 = Arrays.copyOf(a, a.length / 2);
        int[] b0 = Arrays.copyOf(b, b.length/2);
        int[] c = mult(a0, b0);
        int n = a.length/2;
        // special case: if a=Fn-1, add b*2^2^n which is the same as subtracting b
        if (a[n] == 1)
            subModFn(c, Arrays.copyOf(b0, c.length), n*32);
        if (b[n] == 1)
            subModFn(c, Arrays.copyOf(a0, c.length), n*32);
        return c;
    }

    public static int[] mult(int[] a, int[] b) {
        return mult(a, a.length*32, b, b.length*32);
    }

    static void modFn(int[] a) {
        int len = a.length;
        boolean carry = false;
        for (int i=0; i<len/2; i++) {
            int bi = a[len/2+i];
            int diff = a[i] - bi;
            if (carry)
                diff--;
            carry = ((diff>>>31) > (a[i]>>>31)-(bi>>>31));   // carry if signBit(diff) > signBit(a)-signBit(b)
            a[i] = diff;
        }
        for (int i=len/2; i<len; i++)
            a[i] = 0;
        // if result is negative, add Fn; since Fn ≡ 1 (mod 2^n), it suffices to add 1
        if (carry) {
            int j = 0;
            do {
                int sum = a[j] + 1;
                a[j] = sum;
                carry = sum == 0;
                j++;
                if (j >= a.length)
                    j = 0;
            } while (carry);
        }
    }

    static void modFn(int[][] a) {
        for (int i=0; i<a.length; i++)
            modFn(a[i]);
    }

    static int[] cyclicShiftRight(int[] a, int numBits) {
        int[] b = new int[a.length];
        int numElements = numBits / 32;
        System.arraycopy(a, numElements, b, 0, a.length-numElements);
        System.arraycopy(a, 0, b, a.length-numElements, numElements);

        numBits = numBits % 32;
        if (numBits != 0) {
            int b0 = b[0];
            b[0] = b[0] >>> numBits;
            for (int i=1; i<b.length; i++) {
                b[i-1] |= b[i] << (32-numBits);
                b[i] = b[i] >>> numBits;
            }
            b[b.length-1] |= b0 << (32-numBits);
        }
        return b;
    }

    static int[] cyclicShiftLeftBits(int[] a, int numBits) {
        int[] b = cyclicShiftLeftElements(a, numBits/32);

        numBits = numBits % 32;
        if (numBits != 0) {
            int bhi = b[b.length-1];
            b[b.length-1] <<= numBits;
            for (int i=b.length-1; i>0; i--) {
                b[i] |= b[i-1] >>> (32-numBits);
                b[i-1] <<= numBits;
            }
            b[0] |= bhi >>> (32-numBits);
        }
        return b;
    }

    static int[] cyclicShiftLeftElements(int[] a, int numElements) {
        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, numElements, a.length-numElements);
        System.arraycopy(a, a.length-numElements, b, 0, numElements);
        return b;
    }

    static void addShifted(int[] a, int[] b, int numElements) {
        boolean carry = false;
        int i = 0;
        while (i < Math.min(b.length, a.length-numElements)) {
            int ai = a[i+numElements];
            int sum = ai + b[i];
            if (carry)
                sum++;
            carry = ((sum>>>31) < (ai>>>31)+(b[i]>>>31));   // carry if signBit(sum) < signBit(a)+signBit(b)
            a[i+numElements] = sum;
            i++;
        }
        while (carry) {
            a[i+numElements]++;
            carry = a[i+numElements] == 0;
            i++;
        }
    }

    private static void addModPow2(int[] a, int[] b, int numBits) {
        int numElements = (numBits+31) / 32;
        boolean carry = false;
        int i;
        for (i=0; i<numElements; i++) {
            int sum = a[i] + b[i];
            if (carry)
                sum++;
            carry = ((sum>>>31) < (a[i]>>>31)+(b[i]>>>31));   // carry if signBit(sum) < signBit(a)+signBit(b)
            a[i] = sum;
        }
        a[i-1] &= -1 >>> (32-(numBits%32));
        for (; i<a.length; i++)
            a[i] = 0;
    }

    static void appendBits(int[] a, int aBitLength, int[] b, int bStart, int bBitLength) {
        int aIdx = aBitLength / 32;
        int bit32 = aBitLength % 32;

        for (int i=bStart; i<bStart+bBitLength/32; i++) {
            if (bit32 > 0) {
                a[aIdx] |= b[i] << bit32;
                aIdx++;
                a[aIdx] = b[i] >>> (32-bit32);
            }
            else {
                a[aIdx] = b[i];
                aIdx++;
            }
        }

        if (bBitLength%32 > 0) {
            int bIdx = bBitLength / 32;
            int bi = b[bStart+bIdx];
            bi &= -1 >>> (32-bBitLength);
            a[aIdx] |= bi << bit32;
            if (bit32+(bBitLength%32) > 32)
                a[aIdx+1] = bi >>> (32-bit32);
        }
    }

    private static int[][] splitBits(int[] a, int bitLength) {
        int aIntIdx = 0;
        int aBitIdx = 0;
        int numPieces = (a.length*32+bitLength-1) / bitLength;
        int pieceLength = (bitLength+31) / 32;   // in ints
        int[][] b = new int[numPieces][pieceLength];
        for (int i=0; i<b.length; i++) {
            int bitsRemaining = Math.min(bitLength, a.length*32-i*bitLength);
            int bIntIdx = 0;
            int bBitIdx = 0;
            while (bitsRemaining > 0) {
                int bitsToCopy = Math.min(32-aBitIdx, 32-bBitIdx);
                bitsToCopy = Math.min(bitsRemaining, bitsToCopy);
                int mask = a[aIntIdx] >>> aBitIdx;
                mask &= -1 >>> (32-bitsToCopy);
                mask <<= bBitIdx;
                b[i][bIntIdx] |= mask;
                bitsRemaining -= bitsToCopy;
                aBitIdx += bitsToCopy;
                if (aBitIdx >= 32) {
                    aBitIdx -= 32;
                    aIntIdx++;
                }
                bBitIdx += bitsToCopy;
                if (bBitIdx >= 32) {
                    bBitIdx -= 32;
                    bIntIdx++;
                }
            }
        }
        return b;
    }

    private static int[][] splitInts(int[] a, int numPieces, int pieceSize, int targetPieceSize) {
        int[][] ai = new int[numPieces][targetPieceSize];
        for (int i=0; i<a.length/pieceSize; i++)
            System.arraycopy(a, i*pieceSize, ai[i], 0, pieceSize);
        System.arraycopy(a, a.length/pieceSize*pieceSize, ai[a.length/pieceSize], 0, a.length%pieceSize);
        return ai;
    }

    static int[] multKaratsuba(int[] a, int[] b) {
        int n = Math.max(a.length, b.length);
        if (n <= 32)
            return multSimple(a, b);
        else {
            int n1 = (n+1) / 2;
            int n1a = Math.min(n1, a.length);
            int n1b = Math.min(n1, b.length);

            int[] a1 = Arrays.copyOf(a, n1a);
            int[] a2 = n1a>=a.length ? new int[0] : Arrays.copyOfRange(a, n1a, n);
            int[] b1 = Arrays.copyOf(b, n1);
            int[] b2 = n1b>=b.length ? new int[0] : Arrays.copyOfRange(b, n1b, n);

            int[] A = addExpand(a1, a2);
            int[] B = addExpand(b1, b2);

            int[] c1 = multKaratsuba(a1, b1);
            int[] c2 = multKaratsuba(a2, b2);
            int[] c3 = multKaratsuba(A, B);
            c3 = subExpand(c3, c1);   // c3-c1>0 because a and b are positive
            c3 = subExpand(c3, c2);   // c3-c2>0 because a and b are positive

            int[] c = Arrays.copyOf(c1, Math.max(n1+c3.length, 2*n1+c2.length));
            addShifted(c, c3, n1);
            addShifted(c, c2, 2*n1);

            return c;
        }
    }


    private static int[] addExpand(int[] a, int[] b) {
        int[] c = Arrays.copyOf(a, Math.max(a.length, b.length));
        boolean carry = false;
        int i = 0;
        while (i < Math.min(b.length, a.length)) {
            int sum = a[i] + b[i];
            if (carry)
                sum++;
            carry = ((sum>>>31) < (a[i]>>>31)+(b[i]>>>31));   // carry if signBit(sum) < signBit(a)+signBit(b)
            c[i] = sum;
            i++;
        }
        while (carry) {
            if (i == c.length)
                c = Arrays.copyOf(c, c.length+1);
            c[i]++;
            carry = c[i] == 0;
            i++;
        }
        return c;
    }

    private static int[] subExpand(int[] a, int[] b) {
        int[] c = Arrays.copyOf(a, Math.max(a.length, b.length));
        boolean carry = false;
        int i = 0;
        while (i < Math.min(b.length, a.length)) {
            int diff = a[i] - b[i];
            if (carry)
                diff--;
            carry = ((diff>>>31) > (a[i]>>>31)-(b[i]>>>31));   // carry if signBit(diff) > signBit(a)-signBit(b)
            c[i] = diff;
            i++;
        }
        while (carry) {
            c[i]--;
            carry = c[i] == -1;
            i++;
        }
        return c;
    }

    static int[] multSimple(int[] a, int[] b) {
        int[] c = new int[a.length+b.length];
        long carry = 0;
        for (int i=0; i<c.length; i++) {
            long ci = c[i] & 0xFFFFFFFFL;
            for (int k=Math.max(0,i-b.length+1); k<a.length&&k<=i; k++) {
                long prod = (a[k]&0xFFFFFFFFL) * (b[i-k]&0xFFFFFFFFL);
                ci += prod;
                carry += ci >>> 32;
                ci = ci << 32 >>> 32;
            }
            c[i] = (int)ci;
            if (i < c.length-1)
                c[i+1] = (int)carry;
            carry >>>= 32;
        }
        return c;
    }

    public static int[] toIntArray(BigInteger a) {
        byte[] aArr = a.toByteArray();
        int[] b = new int[(aArr.length+3)/4];
        for (int i=0; i<aArr.length; i++)
            b[i/4] += (aArr[aArr.length-1-i]&0xFF) << ((i%4)*8);
        return b;
    }

    public static BigInteger toBigInteger(int[] a) {
        byte[] b = new byte[a.length*4];
        for (int i=0; i<a.length; i++) {
            int iRev = a.length - 1 - i;
            b[i*4] = (byte)(a[iRev] >>> 24);
            b[i*4+1] = (byte)((a[iRev]>>>16) & 0xFF);
            b[i*4+2] = (byte)((a[iRev]>>>8) & 0xFF);
            b[i*4+3] = (byte)(a[iRev] & 0xFF);
        }
        return new BigInteger(1, b);
    }

    static void subModPow2(int[] a, int[] b, int numBits) {
        int numElements = (numBits+31) / 32;
        boolean carry = false;
        int i;
        for (i=0; i<numElements; i++) {
            int diff = a[i] - b[i];
            if (carry)
                diff--;
            carry = ((diff>>>31) > (a[i]>>>31)-(b[i]>>>31));   // carry if signBit(diff) > signBit(a)-signBit(b)
            a[i] = diff;
        }
        a[i-1] &= -1 >>> (32-(numBits%32));
        for (; i<a.length; i++)
            a[i] = 0;
    }

    private static void subModFn(int[] a, int[] b, int pow2n) {
        addModFn(a, cyclicShiftLeftElements(b, pow2n/32));
    }

    static void idft(int[][] A, int m, int n) {
        boolean even = m%2 == 0;
        int len = A.length;
        int v = n - 1;
        for (int slen=1; slen<=len/2; slen*=2) {   // slen = #consecutive coefficients for which the sign (add/sub) and x are constant
            for (int j=0; j<len; j+=2*slen) {
                int idx = j;
                int idx2 = idx + slen;   // idx2 is always idx+slen
                int x = getIdftExponent(n, v, idx, even);

                for (int k=slen-1; k>=0; k--) {
                    int[] c = A[idx].clone();
                    addModFn(A[idx], A[idx2]);
                    A[idx] = cyclicShiftRight(A[idx], 1);

                    subModFn(c, A[idx2], 1<<n);
                    A[idx2] = cyclicShiftRight(c, x);
                    idx++;
                    idx2++;
                }
            }

            v--;
        }
    }

    static void dft(int[][] A, int m, int n) {
        boolean even = m%2 == 0;
        int len = A.length;
        int v = 1;

        for (int slen=len/2; slen>0; slen/=2) {   // slen = #consecutive coefficients for which the sign (add/sub) and x are constant
            for (int j=0; j<len; j+=2*slen) {
                int idx = j;
                int x = getDftExponent(n, v, idx+len, even);

                for (int k=slen-1; k>=0; k--) {
                    int[] d = cyclicShiftLeftBits(A[idx+slen], x);
                    int[] c = A[idx].clone();
                    addModFn(A[idx], d);
                    subModFn(c, d, 1<<n);
                    A[idx+slen] = c;
                    idx++;
                }
            }

            v++;
        }
    }

    private static int[] mult(int[] a, int aBitLen, int[] b, int bBitLen) {
        int bitLength = Math.max(aBitLen, bBitLen);
        if (bitLength < 93600 || (bitLength >= 131072 && bitLength < 159300))
        {
            return multKaratsuba(a, b);
        }
        // set M to the number of binary digits in a or b, whichever is greater
        int M = Math.max(aBitLen, bBitLen);

        // find the lowest m such that m>=log2(2M)
        int m = 32 - Integer.numberOfLeadingZeros(2*M-1-1);

        int n = m/2 + 1;

        // split a and b into pieces 1<<(n-1) bits long; assume n>=6 so pieces start and end at int boundaries
        boolean even = m%2 == 0;
        int numPieces = even ? 1<<n : 1<<(n+1);
        int pieceSize = 1 << (n-1-5);   // in ints

        // build u and v from a and b, allocating 3n+5 bits in u and v per n+2 bits from a and b, resp.
        int numPiecesA = (a.length+pieceSize) / pieceSize;
        int[] u = new int[(numPiecesA*(3*n+5)+31)/32];
        int uBitLength = 0;
        for (int i=0; i<numPiecesA && i*pieceSize<a.length; i++) {
            appendBits(u, uBitLength, a, i*pieceSize, n+2);
            uBitLength += 3*n+5;
        }
        int numPiecesB = (b.length+pieceSize) / pieceSize;
        int[] v = new int[(numPiecesB*(3*n+5)+31)/32];
        int vBitLength = 0;
        for (int i=0; i<numPiecesB && i*pieceSize<b.length; i++) {
            appendBits(v, vBitLength, b, i*pieceSize, n+2);
            vBitLength += 3*n+5;
        }

        int[] gamma = mult(u, uBitLength, v, vBitLength);
        int[][] gammai = splitBits(gamma, 3*n+5);
        int halfNumPcs = numPieces / 2;

        int[][] zi = new int[gammai.length][];
        for (int i=0; i<gammai.length; i++)
            zi[i] = gammai[i];
        for (int i=0; i<gammai.length-halfNumPcs; i++)
            subModPow2(zi[i], gammai[i+halfNumPcs], n+2);
        for (int i=0; i<gammai.length-2*halfNumPcs; i++)
            addModPow2(zi[i], gammai[i+2*halfNumPcs], n+2);
        for (int i=0; i<gammai.length-3*halfNumPcs; i++)
            subModPow2(zi[i], gammai[i+3*halfNumPcs], n+2);

        // zr mod Fn
        int[][] ai = splitInts(a, halfNumPcs, pieceSize, 1<<(n+1-5));
        int[][] bi = splitInts(b, halfNumPcs, pieceSize, 1<<(n+1-5));
        dft(ai, m, n);
        dft(bi, m, n);
        modFn(ai);
        modFn(bi);
        int[][] c = new int[halfNumPcs][];
        for (int i=0; i<c.length; i++)
            c[i] = multModFn(ai[i], bi[i]);
        idft(c, m, n);
        modFn(c);

        int[] z = new int[1<<(m+1-5)];
        // calculate zr mod Fm from zr mod Fn and zr mod 2^(n+2), then add to z
        for (int i=0; i<halfNumPcs; i++) {
            int[] eta = i>=zi.length ? new int[(n+2+31)/32] : zi[i];

            // zi = delta = (zi-c[i]) % 2^(n+2)
            subModPow2(eta, c[i], n+2);

            // z += zr<<shift = [ci + delta*(2^2^n+1)] << [i*2^(n-1)]
            int shift = i*(1<<(n-1-5));   // assume n>=6
            addShifted(z, c[i], shift);
            addShifted(z, eta, shift);
            addShifted(z, eta, shift+(1<<(n-5)));
        }

        modFn(z);   // assume m>=5
        return z;
    }
}
