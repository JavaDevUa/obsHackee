import { App, Editor, MarkdownView, Modal, Notice, Plugin, PluginSettingTab, Setting } from 'obsidian';

import * as child_process from "child_process";

import * as fs from "fs";


export default class ProSPlugin extends Plugin {

	async onload() {
		console.log('buuuu, BAMAMA')
		console.log('another')

	  this.addCommand({
		id: "id-pros-command",
		name: "pros reload",
		callback: () => {
			console.log("Hey, you!");
      },
    });



	this.addCommand({
  id: 'pros-edit-comd',
  name: 'zuza editor',
  editorCallback: (editor: Editor, view: MarkdownView) => {

	// const sel = editor.getDoc() //returns Object
	const sel = editor.getLine(8)
    //const sel = editor.getSelection()
	//
	
	
	  //console.log('Special Force?')

   // console.log('You have selected: ${sel}');
	console.log('You have selected: ' + sel);


	//const x = editor.createEl("h1", { text: "Heading 1" });
	


//					const child = child_process.spawn("java", ["--version"], {env: process.env, shell: this.usesShell});
//										const child = child_process.exec('java', ['--version'], {env: process.env, shell: this.usesShell});
	//THIS EXEC IS KIND OF OKAY?
	//const child = child_process.exec('java', ['--version']);
	//const child = child_process.spawn('java', ['--version']);

	//const child = child_process.spawn('ls');	//this works, and with stdout
	//const child = child_process.spawn('/usr/share/java');	//this works, and with stdout
	//const child = child_process.spawn('ls', [ '/home/pros' ]);	//this works TOO, I have access to filesystem!!!
	const child = child_process.spawn('/home/pros/.jdks/openjdk-19.0.2/bin/java', [ '--version' ]);	



	const alac = child_process.spawn('alacritty');	




	



	
	console.log('PROS:' + child.stdout)

	child.stdout.on('data', (data) => {
//		console.log('stdout: ${data}')
		console.log('stdout: ' + data)
//

	})


//	fs.promises.writeFile(tempFileName, codeBlockContent).then(() => {
	fs.promises.writeFile('/home/pros/zszszsz', 'helloMan?').then(() => {	//THIS FUCKING WORKS!!! I can write to a file!!!

		console.log('I wrote to file?')
	})





	console.log('new log, it works')



	this.registerMarkdownPostProcessor((element, context) => {

		console.log("HHHHHHHH")
		console.log("X: " + element)


      const codeblocks = element.querySelectorAll("code");

      for (let index = 0; index < codeblocks.length; index++) {
        const codeblock = codeblocks.item(index);
		//codeblock.createEl("h1", { text: "Heading jajaja" });
		//codeblock.createEl("button", { text: "bubub" })

					const pre = codeBlock.parentElement as HTMLPreElement;
const button = this.createRunButton();
//					codeblock.appendChild(button);
		//								pre.appendChild(button);



        //const text = codeblock.innerText.trim();
        //const isEmoji = text[0] === ":" && text[text.length - 1] === ":";

        //if (isEmoji) {
        //  context.addChild(new Emoji(codeblock, text));
        //}
		//context.addChild()

		
      }
    });

  },
})

//this is end
	}

	async onunload() {
	    console.log('out')
	}



	private iterateOpenFilesAndAddRunButtons() {
		this.app.workspace.iterateRootLeaves(leaf => {
			if (leaf.view instanceof FileView) {
				this.addRunButtons(leaf.view.contentEl, leaf.view.file.path);
			}
		})
	}




		/**
	 * Creates a new run button and returns it.
	 *
	 * @returns { HTMLButtonElement } The newly created run button.
	 */
	private createRunButton() {
		console.debug("Add run button");
		const button = document.createElement("button");
	//	button.classList.add(runButtonClass);
		button.setText(buttonText);
		return button;
	}

}

