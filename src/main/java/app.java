import pojo.User;
import service.IAdminService;
import service.IUserService;
import service.impl.AdminServiceImpl;
import service.impl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class app {
    public static void main(String[] args) throws IOException {
//        創建User對象
        User user = new User();
        User user1 = null;
//        創建AdminService對象
        IAdminService adminService = new AdminServiceImpl();
        IUserService userService = new UserServiceImpl();
        //IO
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("注册用户");
        System.out.println("请输入用户名");
        user.setUserName(br.readLine());
        System.out.println("请输入密码");
        user.setPassword(br.readLine());
        System.out.println("請輸入地址");
        user.setAddress(br.readLine());
        System.out.println("請輸入電話");
        user.setTelephone(br.readLine());
        System.out.println("是否爲管理員");
        if(br.readLine().equals("是")){
            user.setIdent("1");
        }else{
            user.setIdent("0");

        }
        if(userService.Reg(user)){
            user1 = adminService.Query(user.getUserName());
            System.out.println("注冊成功,當前用戶信息爲:");
            System.out.println("-------------------------------------");
            System.out.println("  用戶名 :"+user1.getUserName());
            System.out.println("    密碼 :"+user1.getPassword());
            System.out.println("    電話 :"+user1.getTelephone());
            System.out.println("    地址 :"+user1.getAddress());
            String status = user1.getIdent().equals("1") ? "是":"否";
            System.out.println("  管理員 :"+status);
            System.out.println("-------------------------------------");
        }else{
            System.out.println("注册失败，当前用户存在");
        }
        System.out.println("登录");
        System.out.println("请输入用户名");
        user.setUserName(br.readLine());
        System.out.println("请输入密码");
        user.setPassword(br.readLine());
        Boolean loginStatus = userService.Login(user);
        if (loginStatus){
            System.out.println("------------------------------------");
            System.out.println("|功能1                   |功能编号    |");
            System.out.println("------------------------------------");
            System.out.println("|查询所有雇员信息          |    1      |");
            System.out.println("------------------------------------");
            System.out.println("|查询某个雇员信息          |    2      |");
            System.out.println("------------------------------------");
            String functionNumber = br.readLine();
            switch (functionNumber){
                case "1":
                    for (User user2:adminService.Query()){
                        System.out.println("-------------------------------------");
                        System.out.println("  用戶名 :"+user2.getUserName());
                        System.out.println("    密碼 :"+user2.getPassword());
                        System.out.println("    電話 :"+user2.getTelephone());
                        System.out.println("    地址 :"+user2.getAddress());
                        String status = user2.getIdent().equals("1") ? "是":"否";
                        System.out.println("  管理員 :"+status);
                        System.out.println("-------------------------------------");
                    }
                    break;
                case "2":
                    System.out.println("请输入要查询的用户名");
                    User user2 = adminService.Query(br.readLine());
                    System.out.println("-------------------------------------");
                    System.out.println("    KEY :"+user2.getId());
                    System.out.println("  用戶名 :"+user2.getUserName());
                    System.out.println("    密碼 :"+user2.getPassword());
                    System.out.println("    電話 :"+user2.getTelephone());
                    System.out.println("    地址 :"+user2.getAddress());
                    String status = user2.getIdent().equals("1") ? "是":"否";
                    // 三元表达式
                    System.out.println("  管理員 :"+status);
                    System.out.println("-------------------------------------");
                    break;
                default:
                    System.out.println("已退出,本次操作到此结束!");
                    break;
            }
        }
    }
}
