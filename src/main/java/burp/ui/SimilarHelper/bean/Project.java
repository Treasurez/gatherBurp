package burp.ui.SimilarHelper.bean;

import burp.bean.SimilarProjectBean;
import burp.dao.SimilarDomainConfigDao;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private String createTime;
    private List<String> mainDomains = new ArrayList<>();
    private List<Domain> domainEntries = new ArrayList<>();
    private List<URL> urlEntries = new ArrayList<>();

    public Project(SimilarProjectBean bean) {
        this.id = bean.getId();
        this.name = bean.getName();
        this.createTime = bean.getCreateTime();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getMainDomains() {
        return mainDomains;
    }

    public void setMainDomains(List<String> domains) {
        this.mainDomains = new ArrayList<>(domains);
        // 更新数据库
        SimilarDomainConfigDao.saveDomainConfigs(id, domains);
    }

    public List<Domain> getDomainEntries() {
        return domainEntries;
    }

    public List<URL> getUrlEntries() {
        return urlEntries;
    }

    public void addDomainEntry(Domain entry) {
        if (!domainEntries.contains(entry)) {
            domainEntries.add(entry);
        }
    }

    public void addUrlEntry(URL entry) {
        if (!urlEntries.contains(entry)) {
            urlEntries.add(entry);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
