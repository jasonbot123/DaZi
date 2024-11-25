package interface_adapter.profile;

import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

public class ProfilePresenter implements ProfileOutputBoundary {
    private ProfileViewModel viewModel;

    public ProfilePresenter(ProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentProfile(ProfileOutputData outputData) {
        viewModel.setUsername(outputData.getUsername());
        viewModel.setEmail(outputData.getEmail());
        viewModel.setYearOfStudy(outputData.getYearOfStudy());
        viewModel.setProgram(outputData.getProgram());
        viewModel.setBio(outputData.getBio());
        viewModel.setCollege(outputData.getCollege());
        viewModel.setPosts(outputData.getPosts());
    }
}