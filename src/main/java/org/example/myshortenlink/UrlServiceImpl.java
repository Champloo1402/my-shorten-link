package org.example.myshortenlink;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    @Transactional
    public String shortenUrl(String url) {
        var opt = urlRepository.findByUrl(url);
        if (opt.isPresent()) {
            return opt.get().getId().toString();
        }

        UrlRecord urlRecord = new UrlRecord(url);
        urlRepository.save(urlRecord);
        return urlRecord.getId().toString();
    }

    @Transactional
    @Override
    public String getUrlById(Long id) {
        var opt = urlRepository.findById(id);
        if(opt.isEmpty()){
            return null;
        }

        var record = opt.get();
        record.setLastAccess(new Date());
        record.setCount(record.getCount() + 1);
        urlRepository.save(record);

        return record.getUrl();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UrlStatDto> getStatistics() {
        var records = urlRepository.findAll();
        var result = new ArrayList<UrlStatDto>();

        for(var record : records){
            result.add(record.toDto());
        }

        return result;
    }

    @Override
    @Transactional
    public void deleteUrlById(Long id) {
        urlRepository.deleteById(id);

    }
}
