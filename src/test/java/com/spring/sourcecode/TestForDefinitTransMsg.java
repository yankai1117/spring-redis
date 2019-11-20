package com.spring.sourcecode;


/**
 * @ClassName TestForDefinitTransMsg
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/510:30
 * @Version 1.0.0
 */
class TestForDefinitTransMsg<E> {
    /**
     * <E>泛型
     **/
    private String key;
    private E obj;
    private String typeName;
    private String operationName;
    //TransMsg<User> msg = new TransMsg<User>(key,user,this.getClass().getName(),"updateUser");
    public TestForDefinitTransMsg(String keyCode , E object , String className, String optName){
        key = keyCode;
        obj = object;
        typeName = className;
        operationName = optName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public E getObj() {
        return obj;
    }

    public void setObj(E obj) {
        this.obj = obj;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
