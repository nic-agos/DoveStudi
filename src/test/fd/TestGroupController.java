package test.fd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGroupControllerAddMemberSize.class, TestGroupControllerCreateGroupDefaultSize.class,
		TestGroupControllerDeleteGroup.class })
public class TestGroupController {

}
