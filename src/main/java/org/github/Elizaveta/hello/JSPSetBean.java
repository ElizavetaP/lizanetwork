package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.Message;

import java.util.Iterator;
import java.util.List;

public class JSPSetBean {
    private Iterator<Message> it;
    private List<Message> list;

    public JSPSetBean(List<Message> set){
        this.list = set;
    }

    public String getSize(){
        it = list.iterator();
        return Integer.toString(list.size());
    }

    public Message getElement() {
        return it.next();
    }
}
