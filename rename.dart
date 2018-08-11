import 'dart:io';

void main(List<String> args) {
  // List<String> filters = ["@2x", "@2X", "@3x", "@3X"];
  // replaceFileName("D:/Dart", singleFilter: "@2x");
    // replaceFileName("D:/Dart", filters: filters);
  if (args == null || args.length < 2) {
    print(
        "请输入参数,至少输入2个参数， :\n第一个：文件路径(必填)，当前路径可用 . 或者\"\" 代替; \n第二个：要改成什么，必填，要删掉的就用\"\"; \n第三个以上：需要改的字符串,比如要改 @2x 和 @3x 改成 空的，也就是去掉， 则应该输入""; \n例如： dart rename.dart . \"\" @2x 3x");
    return;
  }
  String path = args[0];
  if (path.isEmpty) {
    path = ".";
  }
  String renameTo = args[1];
  // List<String> filters = new List();
  // filters.length =args.length-2;
  //List.copyRange 报错，不明所以
  // List.copyRange(filters, 0, args, 2, args.length);
  //删除2条数据，剩下的就是 filters
  if (args.length > 3) {
    List<String> filters = [];
    for (int i = 2; i < args.length; i++) {
      filters.add(args[i]);
    }
    replaceFileName(path, filters: filters, renameTo: renameTo);
  } else if (args.length == 2) {
    List<String> filters = ["@2x", "@2X", "@3x", "@3X"];
    replaceFileName(path, filters: filters, renameTo: renameTo);
  } else if (args.length == 3) {
    replaceFileName(path, singleFilter: args[2], renameTo: renameTo);
  }
}

void replaceFileName(String path,
    {String singleFilter, List<String> filters, String renameTo}) {
  Directory directory = new Directory(path);
  if (renameTo == null) {
    renameTo = "";
  }
  var files = directory.list(recursive: true).listen((FileSystemEntity entity) {
    String newPath = null;
    if (filters != null) {
      for (String filter in filters) {
        if (entity.path.contains(filter)) {
          newPath = entity.path.replaceAll(filter, renameTo);
          break;
        }
      }
    } else if (singleFilter != null) {
      if (entity.path.contains(singleFilter)) {
        newPath = entity.path.replaceAll(singleFilter, renameTo);
      }
    }
    if (newPath?.isNotEmpty) {
      entity.rename(newPath).then((newFile) {
        print("重命名完成， 源文件名：${entity.path}  新文件名： ${newFile.path}\n");
      });
    }
  });
  
}
