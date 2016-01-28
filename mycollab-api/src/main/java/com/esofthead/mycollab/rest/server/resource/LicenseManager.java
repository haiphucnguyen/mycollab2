package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseType;
import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.esofthead.mycollab.module.support.dao.PremiumUserMapper;
import com.esofthead.mycollab.module.support.domain.PremiumUser;
import com.esofthead.mycollab.module.support.domain.PremiumUserExample;
import com.verhas.licensor.License;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
@RestController
public class LicenseManager {

    @Autowired
    private PremiumUserMapper premiumUserMapper;

    @Autowired
    private MailRelayService mailRelayService;

    @RequestMapping(path = "/register-ee", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String registerEE(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastname,
                             @RequestParam("email") String email, @RequestParam("role") String role,
                             @RequestParam("company") String company, @RequestParam("phone") String phone,
                             @RequestParam("country") String country, @RequestParam("maxUsers") String maxUsers,
                             @RequestParam("website") String website, @RequestParam("isCallable") String isCallable) {
        PremiumUserExample ex = new PremiumUserExample();
        ex.createCriteria().andEmailEqualTo(email);
        int num = premiumUserMapper.countByExample(ex);
        if (num > 0) {
            throw new UserInvalidInputException("There is already user has email " + email + " register the trial " +
                    "product");
        }
        LocalDateTime now = new LocalDateTime();
        PremiumUser premiumUser = new PremiumUser();
        premiumUser.setCompany(company);
        premiumUser.setCountry(country);
        premiumUser.setEmail(email);
        premiumUser.setFirstname(firstName);
        premiumUser.setLastname(lastname);
        premiumUser.setIsphonecall(Boolean.valueOf(isCallable));
        premiumUser.setMaxusers(Integer.parseInt(maxUsers));
        premiumUser.setPhone(phone);
        premiumUser.setRole(role);
        premiumUser.setWebsite(website);
        premiumUser.setRegisterdate(now.toDate());
        premiumUser.setExpiredate(now.plusDays(30).toDate());
        int customerId = premiumUserMapper.insertAndReturnKey(premiumUser);

        LicenseInfo licenseInfo = new LicenseInfo();
        licenseInfo.setCustomerId("" + customerId);
        licenseInfo.setLicenseType(LicenseType.PRO_TRIAL);
        licenseInfo.setIssueDate(now.toDate());
        licenseInfo.setExpireDate(now.plusDays(30).toDate());
        licenseInfo.setLicenseOrg(company);
        licenseInfo.setMaxUsers(premiumUser.getMaxusers());
        String licenseContent = encode(licenseInfo);

        return "Ok";
    }

    private String encode(LicenseInfo licenseInfo) {
        try {
            Properties prop = new Properties();
            prop.setProperty("customerId", licenseInfo.getCustomerId());
            prop.setProperty("licenseType", licenseInfo.getLicenseType().name());
            prop.setProperty("licenseOrg", licenseInfo.getLicenseOrg());
            prop.setProperty("expireDate", DateTimeUtils.formatDateToW3C(licenseInfo.getExpireDate()));
            prop.setProperty("issueDate", DateTimeUtils.formatDateToW3C(licenseInfo.getIssueDate()));
            StringWriter outStream = new StringWriter();
            prop.store(outStream, "");
            String licenseStrInfo = outStream.toString();

            License license = new License();
            license.setLicense(licenseStrInfo);
            InputStream privateStream = LicenseManager.class.getClassLoader().getResourceAsStream("secring.gpg");
            license.loadKey(privateStream, "MyCollab Ltd (The private key for MyCollab license) <hainguyen@mycollab.com>");
            return license.encodeLicense("HanLong1979");
        } catch (Exception e) {
            throw new MyCollabException("Can not generate license", e);
        }
    }
}
