
$ git push
fatal: No configured push destination.
Either specify the URL from the command-line or configure a remote repository using


 git remote add origin https://github.com/enpodi/testmongo.git


$ git push
fatal: The current branch master has no upstream branch.
To push the current branch and set the remote as upstream, use


git commit -m "initial commit"


 git push --set-upstream origin master


https://github.com/enpodi/testthreads.git


echo "# testthreads" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/enpodi/testthreads.git
git push -u origin main




git remote add origin https://github.com/enpodi/testthreads.git
git branch -M main
git push -u origin main
