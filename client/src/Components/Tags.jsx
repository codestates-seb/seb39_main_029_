import styled from "styled-components";
import { IoSearchSharp } from "react-icons/io5";
import ColorButton from "../Assets/ColorBtn";
import "../index";
import { DummyTag } from "../Data/dummyTags";

function Tags() {
  return (
    <Container>
      <Explain>
        <div className="h">Tags</div>
        <div className="p">
          A tag is a keyword or label that categorizes your question with other,
          similar questions. <br />
          Using the right tags makes it easier for others to find and answer
          your question.
        </div>
      </Explain>
      <Search>
        <SearchBar>
          <IoSearchSharp className="searchicon" />
          <SearchInput placeholder="Fliter by tag name ..." />
        </SearchBar>
        <ButtonWrapper>
          <ColorButton mode="GREY" text="A - Z" />
          <ColorButton mode="GREY" text="Z - A" />
        </ButtonWrapper>
      </Search>
      <TagWrapper>
        {DummyTag.map((el) => {
          return (
            <Taglist key={el.id}>
              <ColorButton mode="GREY" text={el.tag} ftsize={12} padding={5} />
              <div>{el.exp}</div>
            </Taglist>
          );
        })}
      </TagWrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  max-width: 1200px;
  height: 100;
`;
const Explain = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 30px;
  .h {
    font-weight: 500;
    font-size: var(--header-font);
    margin: 0 30px 20px 0px;
  }
  .p {
    font-size: var(--text-font);
  }
`;
const Search = styled.div`
  display: flex;
  padding: 20px 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;
const SearchBar = styled.div`
  width: 100;
  border: 1px solid hsl(210, 8%, 85%);
  border-radius: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const SearchInput = styled.input`
  padding: 8px;
  width: 200px;
  border: none;
  :focus {
    outline: none;
  }
`;
const ButtonWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100px;
`;
const TagWrapper = styled.div`
  display: flex;
  flex-wrap: wrap;
  margin: 10px;
  align-items: center;
`;
const Taglist = styled.div`
  overflow: hidden;
  overflow-y: scroll;
  border: 1px solid hsl(210, 8%, 85%);
  border-radius: 5px;
  width: 253px;
  height: 176px;
  margin: 10px;
  padding: 10px;
  font-size: var(--small-font);
  > div {
    margin-top: 10px;
  }
`;

export default Tags;
