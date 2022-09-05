import styled from "styled-components";
import { IoSearchSharp } from "react-icons/io5";
import ColorButton from "../Assets/ColorBtn";
import "../index";
import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Tags() {
  // ? 태그 데이터 받아오기
  const token = localStorage.getItem("accessToken");
  const [Tags, setTags] = useState({});

  useEffect(() => {
    axios
      .get("http://seb039pre029.ga:8080/v1/tags/list", {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        setTags(res.data);
      });
  }, []);

  // ? 검색 기능
  const [search, setSearch] = useState("");

  const onChangeSearch = (e) => {
    e.preventDefault();
    setSearch(e.target.value);
  };

  const onSearch = (e) => {
    e.preventDefault();
    if (search === undefined || search === "") {
      axios
        .get("http://seb039pre029.ga:8080/v1/tags/list", {
          headers: {
            Authorization: token,
          },
        })
        .then((res) => {
          setTags(res.data);
        });
    } else {
      const filteredData = Object.values(Tags).filter((el) =>
        el.name.toUpperCase().includes(search.toUpperCase())
      );
      setTags(filteredData);
    }
  };

  // ? 정렬 기능
  const sortedTags = Object.values(Tags).sort((a, b) =>
    a.name < b.name ? -1 : a.name > b.name ? 1 : 0
  );

  const reversedTags = Object.values(Tags).sort((a, b) =>
    a.name > b.name ? -1 : a.name > b.name ? 1 : 0
  );

  const handleSortedClick = () => setTags(sortedTags);
  const handleReversedClick = () => setTags(reversedTags);

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
          <SearchInput
            type="text"
            placeholder="Fliter by tag name ..."
            value={search}
            onChange={onChangeSearch}
            onKeyUp={(e) => onSearch(e)}
          />
        </SearchBar>
        <ButtonWrapper>
          <ColorButton mode="GREY" text="A - Z" onClick={handleSortedClick} />
          <ColorButton mode="GREY" text="Z - A" onClick={handleReversedClick} />
        </ButtonWrapper>
      </Search>
      <TagWrapper>
        {Object.values(Tags).map((el) => {
          return (
            <Taglist key={el.tagsId}>
              <Link
                className="btn"
                to={{ pathname: `/tagsearch:${el.tagsId}` }}
              >
                <ColorButton
                  mode="GREY"
                  text={el.name}
                  ftsize={12}
                  padding={5}
                />
              </Link>
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
  .btn {
    text-decoration-line: none;
  }
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
