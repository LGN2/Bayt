# Manual merge order

All four PRs target `master`. They are dependent, not independent:

`fixing` → `database` → `frontend` → `spring-ai`

Do not merge or enable automatic merging until their checks and limitations have been reviewed.
Later PRs include prerequisites until the earlier PRs are merged.

## Updating after a squash merge

Record the old tips before changing anything:

```sh
git fetch origin
git tag stack-old-fixing origin/fixing
git tag stack-old-database origin/database
git tag stack-old-frontend origin/frontend
git tag stack-old-ai origin/spring-ai
```

After fixing is squash-merged, replay only each branch's own commits:

```sh
git switch database
git rebase --onto origin/master stack-old-fixing database
git push --force-with-lease origin database
git switch frontend
git rebase --onto database stack-old-database frontend
git push --force-with-lease origin frontend
git switch spring-ai
git rebase --onto frontend stack-old-frontend spring-ai
git push --force-with-lease origin spring-ai
```

Resolve conflicts, rerun verification, and check diffs before pushing. Before the next squash merge,
record new tips with new tag names and repeat for the remaining branches. Rebase-merging can also
change commit identities; use this same procedure when prerequisite SHAs are absent from master.
If history is preserved, merge or rebase the current master into each later branch in order.
Never force-push master. Keep backups until the entire stack has been reviewed.
