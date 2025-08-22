
package util;


import java.util.Scanner;


public class Inputter {
    private static Scanner sc = new Scanner(System.in);
    public static final String VIETTEL_VALID_PHONE = "^03[0-9]{8}$";
    public static final String MOBI_VALID_PHONE = "^09[0-9]{8}$";
    public static final String VINA_VALID_PHONE = "^07[0-9]{8}$";
    //public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //nhập vào 1 số có kèm câu mời và câu chửi
    public static int  inputInt(String inputMsg, String errMsg){
        while(true){
            try{
                System.out.printf(inputMsg);
                int value = Integer.parseInt(sc.nextLine());
                return value;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //nhập vào 1 số kèm câu mời + câu chửi + giới hạn 
     public static int  inputInt(String inputMsg, String errMsg,
            int lowerBound, int upperBound){
        while(true){
            try{
                System.out.printf(inputMsg);
                int value = Integer.parseInt(sc.nextLine());
                if(value < lowerBound || value > upperBound){
                    throw new Exception();
                }
                return value;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
      //hàm nhận số check dkien số 
    
     //nhập vào chuỗi kèm câu mời , câu chửi 
    public static String inputString(String inputMsg, String errMsg){
        while(true){
            try{
                System.out.printf(inputMsg);
                String str = sc.nextLine();
                if(str.isEmpty()){
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
     //nhập vào chuỗi kèm câu mời , câu chửi , câu regex
    public static String inputString(String inputMsg, String errMsg,
            String regex){
        while(true){
            try{
                System.out.printf(inputMsg);
                String str = sc.nextLine();
                if(str.isEmpty() || !str.matches(regex)){
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //hàm nhận vào 1 câu mời, câu chởi ,regex , giới hạn kí tự của chuỗi 
    public static String inputString(String inputMsg, String errMsg,
                 String regex,int lowerLength, int UpperLength){
        while(true){
            try{
                System.out.printf(inputMsg);
                String str = sc.nextLine();
                if(str.isEmpty() || !str.matches(regex)|| str.length() < lowerLength || str.length() > UpperLength){
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
     // hàm nhận vào 1 douible number 
    public static double inputDoubleString (String inputMsg, String errMsg){
        while(true){
            try{
                System.out.printf(inputMsg);
                double value = Double.parseDouble(sc.nextLine());
                return value;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //hàm nhận vào 1 double và điều kiện 
     public static double inputDoubleString (String inputMsg, String errMsg,
                                    double  lowerBound , double upperBound){
        while(true){
            try{
                System.out.printf(inputMsg);
                double value = Double.parseDouble(sc.nextLine());
                if(value < lowerBound || value > upperBound){
                    throw new Exception();
                }
                return value;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    
     // hàm nhận vào phone và xử lý theo mã đt VN
    public static String inputPhoneNumber(String inputMsg, String errMsg,String regex){
        String phone = "";
        while (true) {
            System.out.println(inputMsg);
            phone = sc.nextLine();
            if (phone.matches(regex)) {
                if (phone.matches(VIETTEL_VALID_PHONE) || phone.matches(MOBI_VALID_PHONE)
                        || phone.matches(VINA_VALID_PHONE)) {
                    break;
                }
            } else {
                System.out.println(errMsg);
            }
        }
        return phone;
    }
    // hàm nhận vào email
    //create method to input mail
    public static String inputEmail(String inputMsg, String errMsg) {
        String email = "";
        while (true) {
            System.out.print(inputMsg);
            email = sc.nextLine();
            if (email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$") || email.matches("^[a-zA-Z0-9._%+-]+@yahoo\\.com$") || email.matches("^[a-zA-Z0-9._%+-]+@hotmail\\.com$")
                    || email.matches("^[a-zA-Z0-9._%+-]+@outlook\\.com$") || email.matches("^[a-zA-Z0-9._%+-]+@icloud\\.com$")) {
                break;
            } else {
                System.out.println(errMsg);
            }
        }
        return email;
    }
    
}
