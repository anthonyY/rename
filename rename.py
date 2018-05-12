# coding:utf8
import os;
import sys;
import types;
argvLen = len(sys.argv)
path = os.getcwd();

"""
重命名
file 文件名
key要更名的文字
renameTo 要更名成为什么字
path 路径
"""  
def rename(file, key, renameTo, path):

    # 原来的文件路径
    olddir = os.path.join(path, file);
    newFile = file.replace(key,renameTo)
    # # 文件名
    newdir = os.path.join(path, newFile);
    is_exist = os.path.exists(newdir)
    if not(is_exist):
        # 重命名
        os.rename(olddir, newdir);
    else :
        print ("文件已存在"+newdir)

"""
遍历文件夹重命名
path 路径
key 要更名的参数，这里是专门为@2x, @3x 准备的方法，所以keys是个元组（@2x, @3x）
"""
def renameFilePath(path, keys):
    print(path)
    i = 0;
    
    filelist = os.listdir(path);  # 该文件夹下所有的文件（包括文件夹）
    for file in filelist:  # 遍历所有文件
        i = i + 1;
        olddir = os.path.join(path, file);  # 原来的文件路径
        if os.path.isdir(olddir):  # 如果是文件夹则跳过
            renameFilePath(olddir, keys)
            continue;
        else :
            for key in keys:
                if key in file:
                    rename(file, key, "", path)


"""
更名@2x 和@3x 的方法
"""
def rename2x3x(path):
    key2x = "@2x"
    key3x = "@3x"
    keys = (key2x, key3x)
    renameFilePath(path, keys)

"""
遍历文件夹重命名
path 路径
key要更名的文字
renameTo 要更名成为什么字
"""
def renameFromPath(path, key, renameTo):
    filelist = os.listdir(path);  # 该文件夹下所有的文件（包括文件夹）
    print("path="+path)
    for file in filelist:  # 遍历所有文件
        olddir = os.path.join(path, file);  # 原来的文件路径
        if os.path.isdir(olddir): 
            renameFromPath(olddir, key, renameTo)
        elif key in file:
            rename(file, key, renameTo, path)
        

#接收命令行传过来的参数
#如果无参数，默认重命名@2x @3x 为"", 也就是去掉@2x @3x
if argvLen < 3:
    rename2x3x(path)

else :
    key = sys.argv[1]
    renameTo = sys.argv[2]
    renameFromPath(path, key, renameTo)
   
