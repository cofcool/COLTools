# COLTools


<!-- @import "[TOC]" {cmd="toc" depthFrom=3 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [dhtml2text](#dhtml2text)

<!-- /code_chunk_output -->

### dhtml2text

该脚本可以下载指定页面下的所有a标签对应的链接，也可把下载下来的html页面合并为纯文本文件。

![dhtml2text](./imgs/dhtml2text-01.png)

脚本使用Python3, 网页转文本使用`html2text`完成。

使用:

```
# 安装依赖
pip3 install html2text

# 运行
python3 dhtml2text.py
```
