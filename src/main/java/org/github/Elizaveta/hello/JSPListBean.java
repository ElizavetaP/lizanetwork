package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.Message;

import java.util.Iterator;
import java.util.List;

public class JSPListBean {
    private Iterator<Message> it;
    private List<Message> list;

    public JSPListBean(List<Message> list){
        this.list = list;
    }

    public String getSize(){
        it = list.iterator();
        return Integer.toString(list.size());
    }

    public Message getElement() {
        return it.next();
    }
}
