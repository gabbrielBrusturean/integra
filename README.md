## Branch and Commit Conventions

To keep the repository organized and easy to follow, all team members should use the same lightweight conventions for branch names and commit messages.

### Branch Naming

Branch names should follow this format:

```text
<type>/INT-XX-short-description
```

Allowed branch types:

- `feature` - for new functionality
- `bug` - for bug fixes
- `test` - for test-related changes
- `docs` - for adding documentation

The task code should match the Trello/Jira task ID, for example `INT-05`.

The description should be short, clear, and written in lowercase using hyphens between words.

Examples:

```text
feature/INT-05-define-domain-model
feature/INT-12-add-event-list
bug/INT-18-fix-registration-validation
test/INT-21-add-event-service-tests
```

### Commit Messages

Commit messages should be short, clear, and describe what changed.

There is no strict commit format required, but messages should be concise and meaningful.

Good examples:

```text
Add initial Event model
Define user roles for MVP
Fix registration validation
Add basic tests for event service
Update README with branch conventions
```

Avoid vague messages such as:

```text
changes
fix
update
stuff
wip
```

### Summary

Use clear branch names and commit messages so that everyone can understand the purpose of a change without needing extra context.
