### Quality Policy

## General Principles
- Don't commit too many changes at once (100+ lines of code)
- Commit one change at a time
- If stuck on a process/contribution, don't linger on it. Request help for issues or move on from it.
- After any task is done, please announce in the general chat that it is ready for testing.
- Have at least 5 tasks per Sprint
- Try not to have more than 2 tasks in progress at a time per individual

**GitHub Workflow** (start Sprint 1)

### Branch Structure
- **main**: Root branch, must remain stable at all times
- **dev**: Development branch, merge to main only after end of sprint
- **US#-<short description>**: User Story branches must relate directly to board User Stories, branched from dev
- **US#-Task#-<short description>** (ALTERNATIVE): Sub-task of User Story, branched from related User Story
- Do not delete the above types of branches, however, you can delete temporary branches

### Commit Messages
- Include US# and Task# with intentful message
- Can include "wip" to show work in progress
- Commit often, pull often

### Pull Request Process
1. Create pull request to merge changes
2. Fill out the Pull Request with relevant information
3. Request review from all team members
4. Address all reviewer comments
5. Git Master can perform final approval 
6. Merge PRs to main/dev

### Merging Rules
- Merging to main and dev should be done only through Pull Requests
- Pull Requests to main should be merged by the Git Master themselves
- All Pull Requests to dev and main requires a review by at least one team member

### Git Master Responsibilities
- Notify and handly any left behind git conflicts
- Ensure necessary Pull Requests have reviews
- Final say of all Pull Requests to main/dev

### Individual Workflow
1. Branch from Dev into your US# branch
2. Work on new US branch
3. Commit frequently, even if incomplete
4. Push often
5. When User Story is done, merge current Dev into your US branch
6. Test thoroughly after merging Dev
7. Create Pull Request to dev and request review from everyone
8. If reviewed and approved, merge into dev
9. When sprint is done and features are complete, create PR to main
10. Git Master merges PR to main

**Unit Tests Blackbox** (start Sprint 2)

### Principles
- Test functionality through public facing interfaces only
- Focus on input/output relationships
- Validate correct/incorrect behavior based on task/story requirements
- Use equivalence partitions and boundary value analysis

### Requirements
- Test necessary code before merging into dev
- Commit message must include "US# Task# Unit Test: <description>"

### Implementation
- Name tests using the pattern: `[MethodName]_[ScenarioBeingTested]_[ExpectedBehavior]`
- Group tests together by feature or component
- Add test folder in src.test.java

**Unit Tests Whitebox** (online: start Sprint 2, campus: start Sprint 3)
  > Describe your Whitebox testing policy 

**Code Review** (online: start Sprint 2, campus: start Sprint 2)

### General Guidelines
- No completed user story should be merged into Dev without a Code Review
- Everyone should perform at least 2 Code Reviews
- Write comments directly in the PR review
- Reviews should be constructive if issues are found

### Pull Request Creator Checklist
- [ ] Does the code accurately fulfill the requirements of the task/story?
- [ ] Does the code follow project standards?
- [ ] Have you added appropriate usage documentation?
- [ ] Have you ensured your code works with existing functionality?

### Code Reviewer Checklist
- [ ] Does the code implement the task/story correctly?
- [ ] Are there any logical issues?
- [ ] Are error cases handled?
- [ ] Is the code maintainable and readable?
- [ ] Are variables and functions named clearly and consistently?
- [ ] Are the tests (if applicable) practical?
- [ ] Is the code free of redundancies?
- [ ] Does the implementation follow project standards?

## Communication Protocol
- All completed tasks should be announced in a chat with:
  - Brief rundown of changes
  - Link to the pull request
  - Testing instructions if necessary

**Static Analysis**  (online: start Sprint 3, campus: start Sprint 3)
  > Your Static Analysis policy   

**Continuous Integration**  (start Sprint 3, campus: start Sprint 3)
  > Your Continuous Integration policy
