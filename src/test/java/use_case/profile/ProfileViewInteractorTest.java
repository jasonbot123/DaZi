package use_case.profile;

import data_access.MongoDBConnection;
import data_access.MongoUserProfileSaveDataAccessObject;
import data_access.MongoUserProfileViewDataAccessObject;
import interface_adapter.profilesave.ProfileSaveController;
import interface_adapter.profilesave.ProfileSavePresenter;
import interface_adapter.profileview.ProfileViewController;
import interface_adapter.profileview.ProfileViewModel;
import interface_adapter.profileview.ProfileViewPresenter;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.ProfileService;
import use_case.profilesave.ProfileSaveDataAccessInterface;
import use_case.profilesave.ProfileSaveInteractor;
import use_case.profileview.*;
import use_case.profilesave.*;

import static org.junit.Assert.assertEquals;

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
