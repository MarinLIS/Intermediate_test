# import json
# import datetime

# def main():
#     manager = NoteManager('notes.json')
#     manager.load()

#     while True:
#         print("1. Создать заметку")
#         print("2. Обновить уже существующую")
#         print("3. Удалить заметку")
#         print("4. Показать все заметки")
#         print("5. Выход")
        
#         option = input("Уточните Ваши действия: ")

#         if option == '1':
#             title = input("Введите заголовок заметки: ")
#             body = input("Заполните тело заметки: ")
#             manager.create(title, body)
#             manager.save()
#         elif option == '2':
#             id = int(input("Введите ID заметки, которую необходимо дополнить / изменить: "))
#             title = input("Введите новый заголовок заметки: ")
#             body = input("Заполните заново тело заметки: ")
#             manager.update(id, title, body)
#             manager.save()
#         elif option == '3':
#             id = int(input("Введите ID заметки, которую необходимо удалить: "))
#             manager.delete(id)
#             manager.save()
#         elif option == '4':
#             manager.display()
#         elif option == '5':
#             break

# class Note:
#     def __init__(self, id, title, body):
#         self.id = id
#         self.title = title
#         self.body = body
#         self.created_at = datetime.datetime.now().isoformat()
#         self.updated_at = self.created_at

#     def update(self, title, body):
#         self.title = title
#         self.body = body
#         self.updated_at = datetime.datetime.now().isoformat()

#     def to_dict(self):
#         return {
#             'id': self.id,
#             'title': self.title,
#             'body': self.body,
#             'created_at': self.created_at,
#             'updated_at': self.updated_at,
#         }

# class NoteManager:
#     def __init__(self, filename):
#         self.notes = []
#         self.filename = filename

#     def load(self):
#         try:
#             with open(self.filename, 'r') as f:
#                 data = json.load(f)
#                 for note_data in data:
#                     note = Note(note_data['id'], note_data['title'], note_data['body'])
#                     note.created_at = note_data['created_at']
#                     note.updated_at = note_data['updated_at']
#                     self.notes.append(note)
#         except FileNotFoundError:
#             pass

#     def save(self):
#         data = [note.to_dict() for note in self.notes]
#         with open(self.filename, 'w') as f:
#             json.dump(data, f)

#     def create(self, title, body):
#         id = len(self.notes) + 1
#         note = Note(id, title, body)
#         self.notes.append(note)

#     def update(self, id, title, body):
#         for note in self.notes:
#             if note.id == id:
#                 note.update(title, body)

#     def delete(self, id):
#         self.notes = [note for note in self.notes if note.id != id]

#     def display(self):
#         for note in self.notes:
#             print(f"ID: {note.id}\nTitle: {note.title}\nBody: {note.body}\nCreated at: {note.created_at}\nUpdated at: {note.updated_at}\n---")



# if __name__ == "__main__":
#     main()

import json
import datetime

notes = []

def create_note(id, title, body):
    note = {
        'id': id,
        'title': title,
        'body': body,
        'created_at': datetime.datetime.now().isoformat(),
        'updated_at': datetime.datetime.now().isoformat()
    }
    notes.append(note)

def update_note(id, title, body):
    for note in notes:
        if note['id'] == id:
            note['title'] = title
            note['body'] = body
            note['updated_at'] = datetime.datetime.now().isoformat()

def delete_note(id):
    global notes
    notes = [note for note in notes if note['id'] != id]

def save_notes(filename):
    with open(filename, 'w', encoding="utf-8") as f:
        json.dump(notes, f)

def load_notes(filename):
    global notes
    try:
        with open(filename, 'r', encoding="utf-8") as f:
            notes = json.load(f)
    except FileNotFoundError:
        pass

def main():
    load_notes('notes.json')

    while True:
        print("1. Create a new note")
        print("2. Update an existing note")
        print("3. Delete a note")
        print("4. Save and exit")

        option = input("Choose an option: ")

        if option == '1':
            id = len(notes) + 1
            title = input("Enter the title of the note: ")
            body = input("Enter the body of the note: ")
            create_note(id, title, body)
        elif option == '2':
            id = int(input("Enter the ID of the note to update: "))
            title = input("Enter the new title of the note: ")
            body = input("Enter the new body of the note: ")
            update_note(id, title, body)
        elif option == '3':
            id = int(input("Enter the ID of the note to delete: "))
            delete_note(id)
        elif option == '4':
            save_notes('notes.json')
            break

if __name__ == "__main__":
    main()