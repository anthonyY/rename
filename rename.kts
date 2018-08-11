import java.io.File

/**
 * 遍历获取路径下的文件
 * @param dir 上层路径
 * @param dirs 存储文件的列表
 * @param condition 条件
 */
fun foreachFiles(dir: File, dirs: MutableList<String>, condition: ((file: File) -> Boolean)? = null) {
    dir.listFiles()?.forEach {
        if (it.isDirectory) {
            foreachFiles(it, dirs, condition)
        } else {
            println(it.name)
            if (condition != null) {
                if (condition(it)) {
                    dirs.add(it.absolutePath)
                }
            } else {
                dirs.add(it.absolutePath)
            }
        }
    }
}

fun isEmpty(str : String?): Boolean{
    if(str == null || str.isEmpty()){
        return true
    }
    return false
}

/**
 * 重命名
 * @param dir 路径
 * @param files 文件列表
 * @param replaceStr 要替换的文字内容
 * @param renameTo 替换后的文字内容，删掉掉可以是 ""
 * @param ignoreCase 是否忽略大小写， 默认否
 *
 */
fun replaceFilenames(dir: File, files : ArrayList<String>, replaceStr : String, renameTo : String, ignoreCase : Boolean= false){
    foreachFiles(dir, files, {it.name.contains(replaceStr, ignoreCase)})
    for(filePath in files){
        val newFileName = filePath.replace(replaceStr, renameTo, ignoreCase)
        File(filePath).renameTo(File(newFileName))
    }
}
var replaceStr : String ?= null
var dirPath = "."
var renameTo : String ?= null
var ignoreCase = false




if(args.size > 0){
    dirPath = args[0]

    if(args.size > 1){
        replaceStr = args[1]
    }
    if(args.size > 2){
        renameTo = args[2]
    }
    if(args.size > 3){
        val params3 = args[3]
        //如果是true 忽略大小写
        ignoreCase = params3.equals("true", true)
    }
}

val dir = File(dirPath)
val files = arrayListOf<String>()
if(!isEmpty(replaceStr)){
    //因为传参数时，无法区分 null 和 ""，所以默认传一个参数就第二个参数就是""
    if(renameTo == null){
        renameTo = ""
    }
    replaceFilenames(dir, files, replaceStr!!, renameTo!!, ignoreCase)
} else {
    //默认替换2x 和@3x为空
    replaceFilenames(dir, files, "@2x", "", ignoreCase)
    replaceFilenames(dir, files, "@3x", "", ignoreCase)
}


