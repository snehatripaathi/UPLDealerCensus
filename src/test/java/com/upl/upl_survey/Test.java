/*

package com.upl.upl_survey;

import com.upl.upl_survey.Manager.CallStatusSerivce;
import com.upl.upl_survey.Manager.PasswordValidation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader= AnnotationConfigContextLoader.class)
@TestPropertySource(locations="classpath:application.properties")
public class Test {


    @Autowired
    PasswordValidation passEncrp;

    @Autowired
    CallStatusSerivce callStatusSerivce;

	@org.junit.Test
	public void sample() {
       String password= passEncrp.decrypt("KPB/TsdMRV1Mui93AWrlUA==");
       System.out.print("******************************************"+password);
        callStatusSerivce.createCallStatusReport();
	}

}



*/
