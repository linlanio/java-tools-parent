# java-core-utils
## 介绍
JAVA代码核心工具类，包括核心、缓存、系统、脚本处理，数据处理等通用工具方法

## 版本历史
```
命令行：
mvn clean install

1.0     2023-09-29      pom文件的parent内增加relativePath配置

1.0     2019-06-18      创建版本，进行结构划分，打包验证及本地发布

```

## 子包简介
1. commons-parent:公共的工具核心、JSON工具、缓存工具类等子包，版本：2.1
2. datas-parent:公共的数据处理核心，多数据源链接等工具类，版本：2.0


## 技术路径
### 软件架构
Springboot

### 安装教程
1. mvn clean install
2. mvn clean deploy

### 使用说明
1. 通过依赖使用
```
   <dependency>
      <groupId>${groupId}</groupId>
      <artifactId>${artifactId}</artifactId>
      <version>${project.version}</version>
      <scope>test</scope>
   </dependency>`
```
2. 直接拷贝打包后的jar包

## 参与贡献
1.  Fork 本仓库
2.  新建 Feat_1.0.0 分支
3.  提交代码
4.  新建 Pull Request
```
git config user.name linlaninfo
git config user.email linlanio@qq.com
git config --global --list
git config --list
```