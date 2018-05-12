import java.io.File;

public class Rename {

    public static void main(String args[]){
        File directory = new File(".");//设定为当前文件夹

        if(args == null || args.length == 0){
            //没有参数，默认重命名去掉2x 3x
            rename2x3x(directory.getAbsolutePath());
        } else {
            rename(directory.getAbsolutePath(), args[1], args[0]);
        }
    }

    public static void rename2x3x(String path){
        String key2x = "@2x";
        String key2xBig = "@2X";
        String key3x = "@3x";
        String key3xBig = "@3X";
        rename(path, "", key2x, key2xBig, key3x, key3xBig);
    }
    public static void rename(String path, String renameTo,  String... keys){
        System.out.println("path:  "+path);
        File directory = new File(path);//设定为当前文件夹

        File[] list = directory.listFiles();

        if(list != null){
            for(File file : list){
                if(file.isDirectory()){
                    rename(file.getAbsolutePath(), renameTo, keys);
                } else {
                    for(String key : keys){
                        if(file.getName().contains(key)){
                            rename(file, key, renameTo);
                        }
                    }
                }
            }
        }
    }
    public static void rename(File file, String key, String renameTo){
        String newFilePath = file.getAbsolutePath().replace(key, renameTo);
        File newFile = new File(newFilePath);
        if(!newFile.exists()){
            file.renameTo(newFile);
        } else {
            System.out.println("文件已存在"+newFilePath);
        }
    }
}
