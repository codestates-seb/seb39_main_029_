// Toast 에디터
import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import colorSyntax from "@toast-ui/editor-plugin-color-syntax";
import "tui-color-picker/dist/tui-color-picker.css";
import "@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css";
import "@toast-ui/editor/dist/i18n/ko-kr";
import { useRef } from "react";

function TextEditor({ setContent }) {
  // Editor DOM 선택용
  const editorRef = useRef();

  return (
    <div>
      <Editor
        ref={editorRef} // DOM 선택용 useRef
        placeholder="내용을 입력해주세요."
        previewStyle="vertical" // 미리보기 스타일 지정
        height="280px" // 에디터 창 높이
        initialEditType="wysiwyg" //
        toolbarItems={[
          // 툴바 옵션 설정
          ["heading", "bold", "italic", "strike"],
          ["hr", "quote"],
          ["ul", "ol", "task", "indent", "outdent"],
          ["table", "image", "link"],
          ["code", "codeblock"],
        ]}
        useCommandShortcut={false} // 키보드 입력 컨트롤 방지
        language="ko-KR"
        plugins={[colorSyntax]}
        onChange={() => {
          setContent(editorRef.current?.getInstance().getMarkdown());
        }}
      ></Editor>
    </div>
  );
}

export default TextEditor;
