package Zad2;

public class Zad2 {
    public boolean checkArmstrong(int x){
        char[] figures = Integer.toString(x).toCharArray();
        int result = 0;
        for( int i = 0; i < figures.length; i++){
            result += Math.pow(figures[i], i+1);
        }
        System.out.println(result);

        if( x == result){
            return true;
        }else{
            return false;
        }
    }
}
