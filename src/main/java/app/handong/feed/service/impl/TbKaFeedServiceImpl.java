package app.handong.feed.service.impl;

import app.handong.feed.domain.TbKaFeed;
import app.handong.feed.dto.TbmessageDto;
import app.handong.feed.mapper.TbmessageMapper;
import app.handong.feed.repository.TbKaFeedRepository;
import app.handong.feed.service.FirebaseService;
import app.handong.feed.service.TbKaFeedService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TbKaFeedServiceImpl implements TbKaFeedService {

    private final TbKaFeedRepository tbKaFeedRepository;
    private final TbmessageMapper tbmessageMapper;
    private final FirebaseService firebaseService;

    public TbKaFeedServiceImpl(
            TbKaFeedRepository tbKaFeedRepository,
            TbmessageMapper tbmessageMapper,
            FirebaseService firebaseService
    ) {
        this.tbKaFeedRepository = tbKaFeedRepository;
        this.tbmessageMapper = tbmessageMapper;
        this.firebaseService = firebaseService;
    }

    public Map<String, Object> create(Map<String, Object> param) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
//        Tbuser tbuser = Tbuser.of(param.get("username") + "", param.get("password") + "");
//        tbuserRepository.save(tbuser);
        System.out.println(param);
        TbKaFeed tbKaFeed = TbKaFeed.of(param.get("id") + "", param.get("chat_id") + "", param.get("user_id") + "", param.get("message") + "", param.get("sent_at") + "", param.get("client_message_id") + "");
        tbKaFeedRepository.save(tbKaFeed);
        returnMap.put("id", tbKaFeed.getId());
        return returnMap;
    }

    public Map<String, Object> getLastKaFeed() {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        TbKaFeed tbKaFeed = tbKaFeedRepository.findTopByOrderBySentAtDesc();
        if (tbKaFeed != null) {
            returnMap.put("id", tbKaFeed.getId());
            returnMap.put("sentAt", tbKaFeed.getSentAt());
        } else {
            returnMap.put("id", 0);
            returnMap.put("sentAt", 0);
        }
        return returnMap;
    }

    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<TbKaFeed> allFeed = tbKaFeedRepository.findAllByDeletedNotOrderBySentAtDesc("Y");

        for (TbKaFeed entry : allFeed) {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("id", entry.getId());
            returnMap.put("message", entry.getMessage());
            returnMap.put("sentAt", entry.getSentAt());
            returnMap.put("createdAt", entry.getCreatedDate());
            returnMap.put("modifiedAt", entry.getModifiedDate());
            result.add(returnMap);
        }

        return result;
    }

    public List<TbmessageDto.Detail> scrollList(String type, String userId) {
        return scrollList(type, Integer.MAX_VALUE, userId);
    }

    public List<TbmessageDto.Detail> scrollList(String type, int afterSentAt, String userId) {
        List<TbmessageDto.Detail> result = tbmessageMapper.scrollList(type, afterSentAt, userId);

        // Process each TbmessageDto.Detail asynchronously
        List<CompletableFuture<TbmessageDto.Detail>> futures = result.stream()
                .map(message -> {
                    // Fetch file details asynchronously
                    CompletableFuture<List<TbmessageDto.FileDetail>> fileDetailsFuture = tbmessageMapper.fileDetailsAsync(message.getId());

                    // When file details are retrieved, process them asynchronously to get signed URLs
                    return fileDetailsFuture.thenCompose(fileDetails -> {
                        // Asynchronously fetch signed URLs for each file
                        List<CompletableFuture<String>> fileFutures = fileDetails.stream()
                                .map(file -> firebaseService.getSignedUrlAsync("KaFile/" + file.getHash() + "." + file.getExt()))
                                .toList();

                        // Combine all futures for signed URLs and update the message when complete
                        return CompletableFuture.allOf(fileFutures.toArray(new CompletableFuture[0]))
                                .thenApply(v -> {
                                    List<String> signedUrls = fileFutures.stream()
                                            .map(CompletableFuture::join)
                                            .collect(Collectors.toList());
                                    message.setFiles(signedUrls);
                                    return message;  // Return updated message
                                });
                    });
                })
                .toList();

        // Wait for all tasks to complete and collect the results
        List<TbmessageDto.Detail> completedMessages = futures.stream()
                .map(CompletableFuture::join)
                .toList();

        // Sort by sentAt in descending order
        return completedMessages.stream()
                .sorted((m1, m2) -> Integer.compare(m2.getSentAt(), m1.getSentAt()))  // Sorting in descending order
                .collect(Collectors.toList());
    }

    public int count(int afterSentAt, String userId) {
        return tbmessageMapper.countAll(afterSentAt, userId);
    }

    public int count(String userId) {
        return count(Integer.MAX_VALUE, userId);
    }

    public TbmessageDto.Detail getOneHash(String hash) {
        TbmessageDto.Detail detail = tbmessageMapper.getOneHash(hash);
        List<TbmessageDto.FileDetail> fileDetail = tbmessageMapper.fileDetails(detail.getId());
        if (!fileDetail.isEmpty()) {
            detail.setFiles(Collections.singletonList(firebaseService.getSignedUrl("KaFile/" + fileDetail.get(0).getHash() + "." + fileDetail.get(0).getExt(), 60 * 24)));
        }
        return detail;

    }


}
