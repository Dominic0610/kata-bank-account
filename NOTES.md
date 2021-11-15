# NOTES: Quick thoughts about the kata implementation
## Which dependencies ?
There are 3 user stories in the kata. We clearly need a BDD testing framework:
- jbehave https://jbehave.org/
- Cucumber https://cucumber.io/
- Cucumber-JVM https://cucumber.io/docs/installation/java/
- Other?

Having some experience with the first one, I'll pick it. May try the others later. New Git branch: *jbehave*.\
Add other useful but minimal dependencies like *Lombok*.\
No persistence, minimal implementation => forget about *Spring* for now.