package {

import flash.display.Sprite;
import flash.text.TextField;

    import org.mousebomb.ane.miad.MiAd;

    public class Main extends Sprite {
    public function Main() {
        var textField:TextField = new TextField();
        textField.text = "Hello, World";
        addChild(textField);

        textField.text = MiAd.getInstance().setDebugMode(true).toString();
        MiAd.getInstance().setKeys("2882303761517230894","af6f2937c564b689eec5620fd3bdedac","c1bc03096c92fc9b992938d415413891");

        MiAd.getInstance().cacheInterstitial();
    }
}
}
