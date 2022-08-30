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
          similar questions. Using the right tags makes it easier for others to
          find and answer your question.
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
              <ColorButton mode="BLUE" text={el.tag} />
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
  height: 100;
  font-family: var(--sans-serif);
  padding: 30px;
`;
const Explain = styled.div`
  display: flex;
  align-items: center;
  justify-content: start;
  padding: 30px;
  .h {
    font-size: var(--header-font);
    font-weight: bold;
    margin: 0 30px 0 30px;
  }
  .p {
    font-size: var(--text-font);
  }
`;
const Search = styled.div`
  display: flex;
  padding: 20px 60px;
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
  justify-content: center;
  align-items: center;
`;
const Taglist = styled.div`
  border: 1px solid hsl(210, 8%, 85%);
  border-radius: 5px;
  width: 300px;
  height: 300px;
  margin: 20px;
  padding: 10px;
`;

export default Tags;
