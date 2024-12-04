package use_case.profile;

import data_access.MongoDBConnection;
import data_access.MongoUserProfileViewDataAccessObject;
import interface_adapter.profileview.ProfileViewModel;
import interface_adapter.profileview.ProfileViewPresenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ProfileService;
import use_case.profileview.*;

public class ProfileViewInteractorTest {

    @Test
    void testLoadProfile() {
        ProfileViewModel viewModel = new ProfileViewModel();

        MongoUserProfileViewDataAccessObject viewDao = new MongoUserProfileViewDataAccessObject(MongoDBConnection.getDatabase("UserDataBase"));
        ProfileViewPresenter viewPresenter = new ProfileViewPresenter(viewModel);
        ProfileService service = new ProfileService();
        ProfileViewInteractor viewInteractor = new ProfileViewInteractor(viewDao, viewPresenter, service);


        // Act

        ProfileViewInputData viewInputData = new ProfileViewInputData("Jason");
        viewInteractor.loadProfile(viewInputData);

        // Assert
        Assertions.assertEquals("2nd Year", viewModel.getYearOfStudy());
        Assertions.assertEquals("Mathematical and physical sciences", viewModel.getProgram());
        Assertions.assertEquals("Hi, my name is Jason!", viewModel.getBio());
        Assertions.assertEquals("University College", viewModel.getCollege());
    }
}
