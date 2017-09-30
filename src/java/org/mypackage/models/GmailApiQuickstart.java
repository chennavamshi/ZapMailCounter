package org.mypackage.models; 

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;



public class GmailApiQuickstart implements Serializable{
    public static int sett = 0;
    public String name;
    public String ans;
// Check https://developers.google.com/gmail/api/auth/scopes for all available scopes
  private static final String SCOPE = "https://www.googleapis.com/auth/gmail.readonly";
  private static final String APP_NAME = "Gmail API Quickstart";
  // Email address of the user, or "me" can be used to represent the currently authorized user.
  private static final String USER = "me";
  // Path to the client_secret.json file downloaded from the Developer Console
  private static final String CLIENT_SECRET_PATH = "\\files\\client_secret.json";
  private String jsonstr = "{\"installed\":{\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"client_secret\":\"C-xmFt6KlZJIhAJrzZmGW1B0\",\"token_uri\":\"https://accounts.google.com/o/oauth2/token\",\"client_email\":\"\",\"redirect_uris\":[\"urn:ietf:wg:oauth:2.0:oob\",\"oob\"],\"client_x509_cert_url\":\"\",\"client_id\":\"1019580494396-ek0b4qi97lovi208h994roqfij30cns4.apps.googleusercontent.com\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\"}}";
  private static GoogleClientSecrets clientSecrets;
    private static Object today;
    private static Object referenceDate;
    public void setName(String s)
    {
            name =s;
    }
  public String getName()
  {
  return name;}
  public static void openWebpage(URI uri) {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public static void openWebpage(URL url) {
    try {
        openWebpage(url.toURI());
    } catch (URISyntaxException e) {
        e.printStackTrace();
    }
}

 public void x() throws IOException, MessagingException {
    HttpTransport httpTransport = new NetHttpTransport();
    JsonFactory jsonFactory = new JacksonFactory();
    
    URL oracle = new URL("https://www.dropbox.com/s/nuzn60yf926y1nb/client_secret.json?dl=0");
          String sss = "https://www.dropbox.com/s/nuzn60yf926y1nb/client_secret.json?dl=0";
       // clientSecrets = GoogleClientSecrets.load(jsonFactory,  new FileReader(new File(sss)));
   // GoogleClientSecrets.
          File f= new File(sss);
    clientSecrets = GoogleClientSecrets.load(jsonFactory,  new StringReader(jsonstr));
  
// Allow user to authorize via url.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, jsonFactory, clientSecrets, Arrays.asList(SCOPE))
        .setAccessType("online")
        .setApprovalPrompt("auto").build();
    String url = flow.newAuthorizationUrl().setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
        .build();
    //System.out.println("Please open the following URL in your browser then type"
      //                 + " the authorization code:\n" + url);
    //URL ur = new URL(url);
    //openWebpage(ur);
    // Read code entered by user.
    
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String code = name;

    // Generate Credential using retrieved code.
    GoogleTokenResponse response = flow.newTokenRequest(code)
        .setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI).execute();
    
    
  
    GoogleCredential credential = new GoogleCredential()
        .setFromTokenResponse(response);
    
    // Create a new authorized Gmail API client
    Gmail service = new Gmail.Builder(httpTransport, jsonFactory, credential)
        .setApplicationName(APP_NAME).build();

    // Retrieve a page of Threads; max of 100 by default.
    ListThreadsResponse threadsResponse = service.users().threads().list(USER).setQ("newer_than:3m in:sent OR Category:Primary ").execute();
   
    
    
    
    List<Thread> threads = threadsResponse.getThreads();
    //ListMessagesResponse x = service.users().messages().list(USER).execute();
    //List<Message> mess = x.getMessages();
     
      Date referenceDate = new Date();
      Calendar c = Calendar.getInstance();  
      c.add(Calendar.MONTH, -3);
      c.add(Calendar.DATE, 1);
      referenceDate = c.getTime();
      Date today = new Date();
      today = Calendar.getInstance().getTime();
    
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ans =  "Start Date: "+ sdf.format(today) + "<br>"; 
        ans = ans + "End Date: "+sdf.format(referenceDate)+ "<br>"; 
        
        //System.out.println("Start Date: "+sdf.format(today));
            //System.out.println("End Date: "+sdf.format(referenceDate));
            Hashtable map = new Hashtable();
            int j=0;
// Print ID of each Thread.
    for (Thread thread : threads) {
      Thread threadq = service.users().threads().get(USER, thread.getId()).execute();
    //System.out.println("Thread id: " + threadq.getId());
    //System.out.println("No. of messages in this thread: " + threadq.getMessages().size());
    List<Message> mm = threadq.getMessages();
    
    for(Message mp : mm)
    {
        //System.out.println(mp.getSnippet());
        Message messagex = service.users().messages().get(USER, mp.getId()).setFormat("raw").execute();
        //System.out.println(messagex.getSnippet());
    
        byte[] emailBytes = Base64.decodeBase64(messagex.getRaw());
          //     System.out.println(emailBytes);    Session session = Session.getDefaultInstance(props, null);

    
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
        //System.out.println("Sent Date " +email.getSentDate());

        Address[] add = email.getFrom();
      //  for (int i=0;i<add.length;i++)
       // System.out.println("Sender " + add[i].toString());
       String str1 = add[0].toString().toLowerCase();
                 if(str1.indexOf("<")!= -1)
                 {
                     int p = str1.indexOf("<");
                     str1 = str1.substring(p+1,str1.length()-1);
                 }
       
        Object s = map.get(str1);
        if(s!=null)
        {
            Object val = map.get(str1);
            map.put(str1,(Integer)val+1);
        }
        else
            map.put(str1,1);
        Address[] recp = email.getAllRecipients();
        if(recp!=null)
        {
        for (int i=0;i<recp.length;i++)
        {
          String str = recp[i].toString().toLowerCase();
                 if(str.indexOf("<")!= -1)
                 {
                     int p = str.indexOf("<");
                     str = str.substring(p+1, str.length()-1);
                 }
                 
            Object obj = map.get(str);
                 
             if(obj!=null)
             {
                Object val = map.get(str);
                map.put(str,(Integer)val+1);
             }
        else
               map.put(str,1);
       
          //  System.out.println("recipients " + recp[i].toString());
        }
        //System.out.println("--------------------------------------------------------------------------------------------");
        }
    }
}
    
   /*
    Set set = map.entrySet();
      // Get an iterator
    Iterator i = set.iterator();
      // Display elements
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
  
    }*/
// System.out.println(threadq.toPrettyString());
       sortValue(map);
       name = ans;   
  }
   public  void sortValue(Hashtable<String, Integer> t){

       //Transfer as List and sort it
       ArrayList<Map.Entry<String, Integer>> l = new ArrayList(t.entrySet());
       Collections.sort(l, new Comparator<Map.Entry<String, Integer>>(){

         public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }});
       Collections.reverse(l);
       for (int i = 1; i < l.size(); i++) {
	    //sett = 1;
	   ans = ans + l.get(i).getValue() + "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp " + l.get(i).getKey() + "<br>"; 
           //System.out.println(l.get(i));
	}
    }
  }
  