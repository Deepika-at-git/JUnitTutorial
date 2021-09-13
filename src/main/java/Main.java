import com.tutorials.MathUtils;

public class Main {

    public static void main(String[] args){

        MathUtils obj = new MathUtils();
        int res = obj.add(1,2);
        if(res==3) {
            System.out.println("Test pass");
        }else{
            System.out.println("Test fail");
        }
    }
}
