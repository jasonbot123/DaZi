package use_case.profile;

import data_access.MongoDBConnection;
import data_access.MongoUserProfileSaveDataAccessObject;
import data_access.MongoUserProfileViewDataAccessObject;
import interface_adapter.profilesave.ProfileSavePresenter;
import interface_adapter.profileview.ProfileViewModel;
import interface_adapter.profileview.ProfileViewPresenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ProfileService;
import use_case.profilesave.ProfileSaveDataAccessInterface;
import use_case.profilesave.ProfileSaveInputData;
import use_case.profilesave.ProfileSaveInteractor;
import use_case.profileview.ProfileViewInputData;
import use_case.profileview.ProfileViewInteractor;

public class ProfileSaveInteractorTest {
    @Test
    void testLoadProfile() {
        ProfileViewModel viewModel = new ProfileViewModel();

        ProfileSaveDataAccessInterface saveDao = new MongoUserProfileSaveDataAccessObject(MongoDBConnection.getDatabase("UserDataBase"));
        ProfileSavePresenter savePresenter = new ProfileSavePresenter(viewModel);
        ProfileSaveInteractor saveInteractor = new ProfileSaveInteractor(saveDao, savePresenter);

        MongoUserProfileViewDataAccessObject viewDao = new MongoUserProfileViewDataAccessObject(MongoDBConnection.getDatabase("UserDataBase"));
        ProfileViewPresenter viewPresenter = new ProfileViewPresenter(viewModel);
        ProfileService service = new ProfileService();
        ProfileViewInteractor viewInteractor = new ProfileViewInteractor(viewDao, viewPresenter, service);

        ProfileSaveInputData saveInputData = new ProfileSaveInputData("Jeremy","3rd Year", "CS", "Loves coding!", "University College");
        saveInteractor.saveProfile(saveInputData);

        ProfileViewInputData viewInputData = new ProfileViewInputData("Jeremy");
        viewInteractor.loadProfile(viewInputData);

        Assertions.assertEquals("3rd Year", viewModel.getYearOfStudy());
        Assertions.assertEquals("CS", viewModel.getProgram());
        Assertions.assertEquals("Loves coding!", viewModel.getBio());
        Assertions.assertEquals("University College", viewModel.getCollege());
    }
}
