/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activite.gui;


import com.mycompagny.myapp.entities.Activite;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.Resources;
import com.codename1.components.ScaleImageLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import com.mycompagny.gui.BaseForm;
import com.mycompagny.myapp.services.ServiceActivite;
import java.io.ByteArrayOutputStream;


/**
 *
 * @author zeine
 */
public class ModifierActivite extends BaseForm{
 String Imagecode;
   String filePath="";
    public ModifierActivite(Resources res, Form current, Activite fi) {
       
        super("Modifier Activite", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Produit");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
         add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(2, 
                            facebook, twitter
                    )
                )
        ));

         TextComponent nom= new TextComponent().label("nom_a");
        add(nom);
        TextComponent cat= new TextComponent().label("cat_age");
        add(cat);
        
        ComboBox<String> combo = new ComboBox<>();
        combo.addItem("Sport");
        combo.addItem("Divertissement");
        combo.addItem("Education");

        add(combo);
        
        //IMAGE
        Font materialFont = FontImage.getMaterialDesignFont();
        FontImage fntImage = FontImage.createFixed("\uE871", materialFont, 0xffffff, 100, 100);
        Button b2 = new Button(fntImage);
        Style fabStyle = b2.getAllStyles();
        fabStyle.setBorder(RoundBorder.create().color(0xf05f5f).shadowOpacity(100));
        fabStyle.setFgColor(0xf15f5f);
        fabStyle.setBgTransparency(50);
        fabStyle.setBgColor(0xf05f5f);
           
        Label lbimg = new Label();
        
         if (DropTarget.isSupported()) {
        DropTarget dnd = DropTarget.create((evt)->{
        String srcFile = (String)evt.getSource();
        System.out.println("Src file is "+srcFile);
       
               String  maChaine = srcFile;
               filePath= maChaine.substring(19,srcFile.length());
               System.out.println(filePath);
                    System.out.println("Location: "+evt.getX()+", "+evt.getY());
                    if (srcFile != null) {
                        try {
                            Image imgg = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile));
                            imgg.scale(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayWidth());
                                lbimg.setIcon(imgg);
                            // c3.removeComponent(imgv);
                            revalidate();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    } 
                }, Display.GALLERY_IMAGE);
            }
         add(b2);
         add(lbimg);
         
         Button Edit = new Button("Ajouter");
        Edit.addActionListener((evt) -> {
                if ((nom.getText().equals("")))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
            ImageIO imgIO = ImageIO.getImageIO();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] ba = out.toByteArray();
            Imagecode = Base64.encode(ba);
            System.out.println(filePath);
            
            ServiceActivite sp = new ServiceActivite();
            //Activite fi = new Activite();
            fi.setNom_a(nom.getText());
            fi.setCat_age(Integer.parseInt(cat.getText()));
           // fi.setCat_age(Integer.valueOf(cat_age.getText()));
            if(!filePath.equals(""))
            {
              fi.setImage(filePath);
            }
            else
            {
              fi.setImage("");
            }
            //fi.setCat_age(cat_age.getSelectedItem());
            fi.setType(combo.getSelectedItem());
            sp.addActivite(fi);
            Dialog.show("Success","Activite modifiee avec success",new Command("OK"));
            new AllActivite(res).show();
                
            }      
        });
        
        addStringValue("", FlowLayout.encloseRightMiddle(Edit));
        
    
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
}
