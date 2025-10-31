**Subject:**

Null pointer Exception when editing the properties of a custom object type again after canceling the first edit.

**Description:**

When the domain object property is of a custom object type, this property can be null, and the user will attempt to edit this property for the first time (which is null at this time). However, at this point, the user will not click Confirm but choose to cancel. After that, if the user chooses to edit this property again instead of leaving the page, the project will report a NullPointerException.

**Steps to Reproduce:**

1. Create a SimpleObject.
2. Open the SimpleObject you just created. At this point, Position is null. Try to modify Position (Position is an enumeration type I defined myself, used to reproduce the problem). I have set Position as a property of SimpleObject.
3. Select the Position attribute for editing, but after editing is completed, do not choose to confirm. Instead, choose to cancel editing and do not leave the page.
4. Select the Position attribute again for editing.

**Expected behavior:**

Open the edit dialog box again without any error.

**Actual behavior:**

Throw NullPointerException.

**Possible root cause:**

During the cancellation operation, the ManagedObject is set to null by restoring the content of the dialog box instead of retaining the original ManagedObjectEmpty. Subsequent editing attempts to call ManagedObject and unconditionally call getMemento().

**Suggested solution:**

1. Add a null value check before calling getMemento () (the getObject() method in the SingleChoiceModel class)

2. Consider not directly setting null values into ManagedProperty after unediting. (The onCancel() method in the InlinePromptContext class)

   

