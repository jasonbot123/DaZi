
package interface_adapter.profileview;

import use_case.profileview.ProfileViewOutputBoundary;
import use_case.profileview.ProfileViewOutputData;

public class ProfileViewPresenter implements ProfileViewOutputBoundary {
    private final ProfileViewModel viewModel;

    public ProfileViewPresenter(ProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentProfile(ProfileViewOutputData outputData) {
        viewModel.setUsername(outputData.getUsername());
        viewModel.setEmail(outputData.getEmail());
        viewModel.setYearOfStudy(outputData.getYearOfStudy());
        viewModel.setProgram(outputData.getProgram());
        viewModel.setBio(outputData.getBio());
        viewModel.setCollege(outputData.getCollege());
    }
}

