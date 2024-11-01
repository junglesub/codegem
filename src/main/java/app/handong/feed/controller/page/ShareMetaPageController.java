package app.handong.feed.controller.page;

import app.handong.feed.dto.TbmessageDto;
import app.handong.feed.exception.NoAuthorizationException;
import app.handong.feed.service.ShortHashService;
import app.handong.feed.service.TbKaFeedService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RequestMapping("/k")
@Controller
public class ShareMetaPageController {


    private final TbKaFeedService tbKaFeedService;

    public ShareMetaPageController(
            TbKaFeedService tbKaFeedService) {
        this.tbKaFeedService = tbKaFeedService;
    }

    @GetMapping("/{hash}")
    public String page(@PathVariable("hash") String hash, Model model) {
        if (hash.length() < 5) return "sharemeta_notfound.html";
        TbmessageDto.Detail detail = tbKaFeedService.getOne(hash);
        if (detail == null) return "sharemeta_notfound.html";
        model.addAttribute("title", detail.getMessage().replaceAll("\\s+", " ").substring(0, 60));
        if (detail.getFiles() != null && !detail.getFiles().isEmpty())
            model.addAttribute("imageUrl", detail.getFiles().get(0));
        else model.addAttribute("imageUrl", "https://feed.handong.app/og-image.jpg");
        model.addAttribute("finalUrl", "https://feed.handong.app/kafeed/" + detail.getId());
        return "sharemeta.html";
    }


}
