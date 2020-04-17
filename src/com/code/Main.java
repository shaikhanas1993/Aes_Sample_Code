package com.code;



public class Main {


    public static void main(String[] args) throws Exception {
	// write your code here
//        InboxSms inboxSms = new InboxSms();
//        inboxSms.set_id("21414");
//        inboxSms.setThread_id("2fqfqf");
//        inboxSms.setAddress("2fqfqf");
//        inboxSms.setAddress("2fqfqf");
          String test = "{'hello':1}";
          FinalJson result =  ClientEncrypt.encrypt(test);
          ServerDecrypt.decrypt(result.getEncryptedString(),result.getEncryptedKey());


//        String result = AESenc.decrypt("UDYFi1G6horUU07J0aUIGJWeESS2dgSmUbfMCzsvx4QyR+JD7nZCdba7nNv+fmAITQVSMIl1V6A8/01Mks5x+ZPv9v87CUrNxEKqUWBPtkxlLSTNNpaWvsCv3W0IMV+Avco2sMl8xebjaBPm8zXe6MgJtEjJxYAYOXG3O/q3iGoz7fUnY7xzIyDqjCRbDWU+CGbkWuQywgYi/cuhv8MJb7rAYQVxNURnEkyhJc5BD6NUloP79RyUsytJH4/jOX8l6m2d3UM1XgfYmnns7wEHvkhIT2PRVvEQmmP23AsW3tHER6n9wWsBgGoet/ru9xzgRb/m0iUvetOFNkiRi83JnbCG8SNuTuY15k2yvSjESR/AZQB3AQOgIgqIxzBOfoNdmWJzqLkKy0wFhWqBMCI9xgXltiTcrJ/UrYKaFAR4rv3P+26vaTCLQa11WrDn4+3Pyh/FwfVXzse4+7//CqIIT2t4/ZsuLwxMlbjtIGV/N5wj1BiENuVTPFidP/yNkjCU");
//       System.out.println(result);
    }
}
