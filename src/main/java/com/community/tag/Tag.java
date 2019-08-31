package com.community.tag;

import com.community.dto.TagDto;

import java.lang.reflect.Array;
import java.util.*;

public class Tag {

    private List<List<String>> tags;
    private  final String STRING1="javascript php css html html5 java node.js python c++ c golang objective-c typescript shell swift c# sass ruby bash less asp.net lua scala coffeescript actionscript rust erlang perl";
    private  final String STRING2="laravel spring express django flask yii ruby-on-rails tornado koa struts";
    private  final String STRING3="linux nginx docker apache ubuntu centos 缓存 tomcat 负载均衡 unix hadoop windows-server";
    private  final String STRING4="mysql redis mongodb sql oracle nosql memcached sqlserver postgresql sqlite";
    private  final String STRING5="git github visual-studio-code vim sublime-text xcode intellij-idea eclipse maven ide svn visual-studio atom emacs textmate hg";
    private  final String STRING6="唱 跳 rap 篮球";

    public Tag(){
        List<List<String>> tags=new ArrayList<>();
        List<String> list1= Arrays.asList(STRING1.split(" "));
        List<String> list2= Arrays.asList(STRING2.split(" "));
        List<String> list3= Arrays.asList(STRING3.split(" "));
        List<String> list4= Arrays.asList(STRING4.split(" "));
        List<String> list5= Arrays.asList(STRING5.split(" "));
        List<String> list6= Arrays.asList(STRING6.split(" "));
        tags.add(list1);
        tags.add(list2);
        tags.add(list3);
        tags.add(list4);
        tags.add(list5);
        tags.add(list6);
        this.tags=tags;
    }

    public List<List<String>> getTags() {
        return tags;
    }

    public List<TagDto> getList() {
        List<TagDto> tagDtoList=new ArrayList<>();
        TagDto tagDto=new TagDto("language",this.tags.get(0));
        TagDto tagDto1=new TagDto("frame",this.tags.get(1));
        TagDto tagDto2=new TagDto("service",this.tags.get(2));
        TagDto tagDto3=new TagDto("db",this.tags.get(3));
        TagDto tagDto4=new TagDto("tools",this.tags.get(4));
        TagDto tagDto5=new TagDto("others",this.tags.get(5));
        tagDtoList.add(tagDto);
        tagDtoList.add(tagDto1);
        tagDtoList.add(tagDto2);
        tagDtoList.add(tagDto3);
        tagDtoList.add(tagDto4);
        tagDtoList.add(tagDto5);
        return tagDtoList;
    }
}
