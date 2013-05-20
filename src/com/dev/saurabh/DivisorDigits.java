    package com.dev.saurabh;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;

    public class DivisorDigits
    {

        public static void main(String [] args)
        {

            try
            {
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                String input = "";
                while((input = consoleReader.readLine()) != "" && input != null)
                {
                    int [] digitCount = new int [10];
                    boolean [] divisor = new boolean[10];
                    for(int i=0; i<input.length(); i++)
                    {
                        int currentDigit = input.charAt(i)-'0';
                        digitCount[currentDigit]++;
                    }
                    divisor[1] = true;
                    for(int i=2; i<10; i++)
                    {
                        divisor[i] = isDivisibleBy(input, i);
                    }

                    int result = 0;
                    for(int i=1; i<10; i++)
                    {
                        if(divisor[i])
                        {
                            result += digitCount[i];
                        }
                    }

                    System.out.println(result);
                }
            }
            catch(Exception exc)
            {
               //exc.printStackTrace();
            }
        }

        private static boolean isDivisibleBy(String input, int divider)
        {
            int length = input.length();
            int rem = 0;
            for(int i=0; i<length; i++)
            {
                rem = (rem*10 + (input.charAt(i)-'0'))%divider;
            }

            return (rem==0);
        }
    }
