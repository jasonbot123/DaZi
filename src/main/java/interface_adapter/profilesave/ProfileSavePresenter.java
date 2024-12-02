package interface_adapter.profilesave;

import interface_adapter.profileview.ProfileViewModel;
import use_case.profilesave.ProfileSaveOutputBoundary;
import use_case.profilesave.ProfileSaveOutputData;

import javax.swing.*;

public class ProfileSavePresenter implements ProfileSaveOutputBoundary {
    private final ProfileViewModel viewModel;

    public ProfileSavePresenter(ProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentProfile(ProfileSaveOutputData outputData) {
        viewModel.setYearOfStudy(outputData.getYearOfStudy());
        viewModel.setProgram(outputData.getProgram());
        viewModel.setBio(outputData.getBio());
        viewModel.setCollege(outputData.getCollege());
    }
}